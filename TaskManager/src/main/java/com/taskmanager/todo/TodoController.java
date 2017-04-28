package com.taskmanager.todo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.taskmanager.model.Todo;
import com.taskmanager.service.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {
	
	TodoService service; 
	
	@Autowired(required=true)
	@Qualifier(value = "todoService")
	public void setTodoService(TodoService todoService) {
		this.service = todoService;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		binder.registerCustomEditor(Date.class, 
						new CustomDateEditor(sdf, false));
	}
	
	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String listTodos(ModelMap model,
							@ModelAttribute("name") String user,
							@RequestParam boolean hideDone) {
		model.addAttribute("todos", service.retrieveTodos(user, hideDone));
		return "list-todos";
	}
	
	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showTodoPage(ModelMap model, 
				@ModelAttribute("name") String user) {
		model.addAttribute("todo", 
				new Todo(0, user, "", 
						new Date(), false));
		return "todo";
	}
	
	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, 
						BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}
		service.addTodo(todo.getName(), todo.getDesc(), todo.getTargetDate(), false);
		model.clear();
		return "redirect:list-todos?hideDone=true";
	}
	
	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(ModelMap model, @RequestParam int id) {
		service.deleteTodo(id);
		model.clear();
		return "redirect:list-todos?hideDone=true";
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String updateTodo(ModelMap model, @RequestParam int id) {
		Todo todo = service.retrieveTodo(id);
		model.addAttribute("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("Errors found!" + result.getAllErrors());
			
			return "todo";
		}
		service.updateTodo(todo);
		model.clear();
		return "redirect:list-todos?hideDone=true";
	}
	
	@RequestMapping(value = "/done-todo", method = RequestMethod.GET)
	public String markDone(ModelMap model, @RequestParam int id) {
		service.markDone(id);
		//System.out.println("Mark DONE complete");
		model.clear();
		return "redirect:list-todos?hideDone=true";
	}
}