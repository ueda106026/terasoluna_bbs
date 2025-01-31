package jp.co.brightstar.app.controller;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jp.co.brightstar.domain.model.Bbs;
import jp.co.brightstar.domain.model.BbsForm;
import jp.co.brightstar.domain.repository.BbsRepository;

@RestController
public class AdministratorController {
	@Autowired
	BbsRepository bbsRepository;
	
	@PostMapping(value = "/administrator_update")
    public Map<String, String> updateAdministrator(@RequestBody Map<String, String> requestData, BbsForm bbsForm) {
		String setAdministrator = requestData.get("administrator");
		bbsForm.setAdministrator(requestData.get("administrator"));
		Bbs bbs = bbsRepository.getReferenceById(1);
		BeanUtils.copyProperties(bbsForm, bbs, "id", "title", "create_date", "update_date");
		bbs = bbsRepository.save(bbs);
		return Map.of("administrator",setAdministrator);
    }
}
