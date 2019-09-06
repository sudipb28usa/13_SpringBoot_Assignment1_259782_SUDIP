package com.boot;


import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SubjectController {
	
    @Autowired
    private SubjectService subjectService;
    
  
    @GetMapping("/")
    public String userForm(Locale locale, Model model) {   	
    	 model.addAttribute("Header", "Please click on below menu");
        return "Home";
    }    	    
     
    @ModelAttribute("subject")
    public Subject formBackingObject() {
        return new Subject();
    }
    
	@RequestMapping(value="/addSubjectPage", method = RequestMethod.GET)
	public String  addSubjectPage(ModelMap model) {
		
		model.addAttribute("Title", "Add Subject");	
		model.addAttribute("Header", "Add Subject");
		
		//return new ModelAndView("AddSubject", "subject", new Subject()); 
		return "AddSubject"; 
	}
 
    @PostMapping("/addSubject")
    public String saveUser(@ModelAttribute("subject") @Valid Subject subject,
                            BindingResult result, Model model) {
  
    	if (result.hasErrors()) {
			model.addAttribute("subjectList", subjectService.listAll());
			return "AddSubject";
		}
    	model.addAttribute("Title", "Home");	
		model.addAttribute("Header", "Please click on below menu.");
		model.addAttribute("message", "Subject has been successfully added");
    	
        subjectService.save(subject);
        return "redirect:/getSubject";
 
    }
    
    
	@RequestMapping(value="/getSubject", method = RequestMethod.GET)
	public String getSubject(ModelMap model) {
		model.addAttribute("subjectList", subjectService.listAll());
		model.addAttribute("message", "");
		model.addAttribute("Title", "Subjects");
		model.addAttribute("Header", "************Subjects*******************");
		return "Subject"; 
	}
	
	@RequestMapping(value="/searchSubject", method = RequestMethod.POST)
	public String searchSubject(@Valid @ModelAttribute("subject")Subject subject,  BindingResult result, ModelMap model) {
 
 		model.addAttribute("subjectList",subjectService.search(subject.getSubtitle()));
 
		model.addAttribute("message", "Search Results are below");
		model.addAttribute("Title", "Subjects");
		model.addAttribute("Header", "************Subjects*******************");
		return "Subject"; 
	}
 
	@RequestMapping("/deleteSubject")
	public String searchSubject(@RequestParam long subjectId) {
		Subject subject=subjectService.findById(subjectId);
		subjectService.delete(subject);
		return "redirect:/getSubject";
	}
	
	
}
