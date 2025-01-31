package jp.co.brightstar.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jp.co.brightstar.domain.repository.BbsRepository;

@RestController
public class AdministratorController {
	@Autowired
	BbsRepository bbsRepository;
	
	@PostMapping(value = "/administrator_update")
    public ResponseEntity<String> updateAdministrator(@RequestBody Map<String, String> requestData) {
        String administrator = requestData.get("administrator");
        System.out.println("Received administrator: " + administrator);
        return ResponseEntity.ok("Success");
    }
}
