package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.model.Instructor;
import peaksoft.service.InstructorService;

import java.util.List;

@Controller

public class InstructorController {
    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/instructors/{id}")
    public String getAllInstructors(@PathVariable Long id, Model model) {
        model.addAttribute("instructorss", instructorService.getAllInstructors(id));
        model.addAttribute("courseId", id);
        return "/instructor/instructors";
    }

    @GetMapping("/instructors/{id}/addInstructor")
    public String addInstructor(@PathVariable Long id, Model model) {
        model.addAttribute("instructor", new Instructor());
        model.addAttribute("courseId", id);
        return "/instructor/addInstructor";
    }

    @PostMapping("/{id}/saveInstructors")
    public String saveInstructor(@ModelAttribute("instructor") Instructor instructor, @PathVariable Long id) {
        instructorService.addInstructor(id, instructor);
        return "redirect:/instructors/" + id;

    }

    @GetMapping("/updateInstructor/{id}")
    public String updateInstructor(@PathVariable("id") Long id, Model model) {
        Instructor instructor = instructorService.getInstructorById(id);
        model.addAttribute("instructor", instructor);
        model.addAttribute("courseId", instructor.getCourse().getId());
        return "/instructor/updateInstructors";
    }

    @PostMapping("{courseId}/{id}/saveUpdateInstructor")
    public String saveUpdateCompany(@PathVariable("courseId") Long courseId, @PathVariable("id")
    Long id, @ModelAttribute("instructor") Instructor instructor) {
        instructorService.updateInstructor(instructor, id);
        return "redirect:/instructors/"+courseId;
    }

    @GetMapping ("/{courseId}/{id}/deleteInstructor")
    public String deleteInstructor(@PathVariable("id") Long id, @PathVariable("courseId")Long courseId){
        instructorService.deleteInstructor(id);
        return "redirect:/instructors/"+courseId;
    }
}