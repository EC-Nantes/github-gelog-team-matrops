package org.centrale.hceres.controller;

import javax.servlet.http.HttpServletRequest;

import org.centrale.hceres.items.Education;
import org.centrale.hceres.items.SeiIndustrialRDContract;
import org.centrale.hceres.service.SeiIndustrialRDContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SeiIndustrialRDContractController {

		
	/**
	 * instanciation
	 */
	@Autowired
	private SeiIndustrialRDContractService seiIndustrialRDContractService;
	
	/**
	 * ajouter un elmt a la base de donnees
	 * @param education : l'elmt a ajouter
	 * @return l'elmt ajoute
	 */
	@PostMapping(value ="/AddSeiIndustrialRDContract")
	public SeiIndustrialRDContract createSeiIndustrialRDContract(@RequestBody Map<String, Object> request) {
		return seiIndustrialRDContractService.saveSeiIndustrialRDContract(request);
	}
}
