package com.samuel.modesto.course.specifications;

import com.samuel.modesto.course.models.Course;
import com.samuel.modesto.course.models.Lesson;
import com.samuel.modesto.course.models.Module;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.UUID;

public class SpecificationTemplate {

    @And({
            @Spec(path = "courseLevel", spec = Equal.class),
            @Spec(path = "courseStatus", spec = Equal.class),
            @Spec(path = "name", spec = Like.class)
    })
    public interface CourseSpec extends Specification<Course> {

    }

    @Spec(path = "title", spec = Like.class)
    public interface ModuleSpec extends Specification<Module> {

    }

    @Spec(path = "title", spec = Like.class)
    public interface LessonSpec extends Specification<Lesson> {

    }

    public static Specification<Module> searchModulesIntoCourse(final UUID courseId) {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            Root<Module> module = root;
            Root<Course> course = query.from(Course.class);
            Expression<Collection<Module>> modulesInCourse = course.get("modules");
            return criteriaBuilder.and(criteriaBuilder.equal(course.get("courseId"), courseId),
                    criteriaBuilder.isMember(module, modulesInCourse));
        };
    }

    public static Specification<Lesson> searchLessonIntoModule(final UUID moduleId) {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            Root<Lesson> lesson = root;
            Root<Module> module = query.from(Module.class);
            Expression<Collection<Lesson>> lessonsInModule = module.get("lessons");
            return criteriaBuilder.and(criteriaBuilder.equal(module.get("moduleId"), moduleId),
                    criteriaBuilder.isMember(lesson, lessonsInModule));
        };
    }
}
