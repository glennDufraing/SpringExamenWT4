package edu.ap.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ap.spring.model.InhaalExamen;
import edu.ap.spring.model.InhaalExamenRepository;
import edu.ap.spring.redis.RedisService;

@RestController
public class ExamController {

	private RedisService service;
	private InhaalExamenRepository repos;
	
	@Autowired
	public void setService(RedisService service) {
		this.service = service;
	}


	@RequestMapping("/{student}/{exam}/{reason}")
	public String getInhaalExaam(@PathVariable("student")String student,@PathVariable("exam")String exam,@PathVariable("reason")String reason,Model model) {
		InhaalExamen newExam = new InhaalExamen(student,exam,reason);
		String key=student+":"+exam+":"+reason;
		if(service.getKey(key)==null) {
			
			service.setKey(key, newExam.getDate());
			
			
		}
		return "added";
	}
	@RequestMapping("/{student}/all")
	public String getInfoStudent(@PathVariable("student")String student){
		Set<String> keys = service.keys(student+"*");
		String tekst = "<html><body><h1>"+student+"</h1></body></html>";
		List<String>sort  =new ArrayList<>();
		for(String item:keys) {
			String[]info = item.split(":");
			sort.add(info[2]+"  "+info[1]+ "  "+service.getKey(item));
		}
		java.util.Collections.sort(sort);
		
		for(String item: sort) {
			tekst+="<p>"+item+"</p>";
		}
		tekst +="</html></body>";
		
		return tekst;
		
		
	}
}