package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import peaksoft.model.Lesson;
import peaksoft.model.Task;
import peaksoft.service.TaskService;

@Controller
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping("/tasks/{id}")
    public String getAllTasks(@PathVariable Long id, Model model) {
        model.addAttribute("tasks", taskService.getAllTasks(id));
        model.addAttribute("lessonId", id);
        return "/task/tasks";
    }

    @GetMapping("/tasks/{id}/addTask")
    public String addTask(@PathVariable Long id, Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("lessonId", id);
        return "/task/addTask";
    }

    @PostMapping("/{id}/saveTask")
    public String saveTask(@ModelAttribute("task") Task task, @PathVariable Long id) {
        taskService.addTask(id, task);
        return "redirect:/tasks/" + id;
    }

    @GetMapping("/updateTask/{id}")
    public String updateTask(@PathVariable("id") Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        model.addAttribute("lessonId", task.getLesson().getId());
        return "/task/updateTask";
    }

    @PostMapping("{lessonId}/{id}/saveUpdateTask")
    public String saveUpdateTask(@PathVariable("lessonId") Long lessonId, @PathVariable("id")
    Long id, @ModelAttribute("task") Task task) {
        taskService.updateTask(task, id);
        return "redirect:/tasks/" + lessonId;
    }

    @GetMapping("/{lessonId}/{id}/deleteTask")
    public String deleteTask(@PathVariable("id") Long id, @PathVariable("lessonId") Long lessonId) {
        taskService.deleteTask(id);
        return "redirect:/tasks/" + lessonId;
    }
}
