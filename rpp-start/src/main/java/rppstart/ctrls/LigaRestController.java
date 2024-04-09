package rppstart.ctrls;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rppstart.jpa.Liga;
import rppstart.repositories.LigaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
@Api(tags = {"Liga CRUD operacije"})
public class LigaRestController {
	
	@Autowired
	private LigaRepository ligaRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("liga")
	@ApiOperation(value = "Vraća kolekciju svih liga iz baze podataka")
	public Collection<Liga> getLige() {
		return ligaRepository.findAll();
	}

	@GetMapping("liga/{id}")
	@ApiOperation(value = "Vraća ligu iz baze podataka čija je id vrednost prosleđena kao path varijabla")
	public Liga getLiga(@PathVariable("id") Integer id) {
		return ligaRepository.getOne(id);
	}
	
	@GetMapping("ligaNaziv/{naziv}")
	@ApiOperation(value = "Vraća kolekciju svih liga iz baze podataka koji u nazivu sadrže string prosleđen kao path varijabla")
	public Collection<Liga> getLigaById(@PathVariable("naziv") String naziv) {
		return ligaRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@PostMapping("liga")
	@ApiOperation(value = "Upisuje ligu u bazu podataka")
	public ResponseEntity<Liga> insertLiga(@RequestBody Liga liga) {
		if (!ligaRepository.existsById(liga.getId())) {
			ligaRepository.save(liga);
			return new ResponseEntity<Liga>(HttpStatus.OK);
		}
		return new ResponseEntity<Liga>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("liga")
	@ApiOperation(value = "Modifikuje postojeću ligu u bazi podataka")
	public ResponseEntity<Liga> updateLiga(@RequestBody Liga liga) {
		if (ligaRepository.existsById(liga.getId())) {
			ligaRepository.save(liga);
			return new ResponseEntity<Liga>(HttpStatus.OK);
		}
		return new ResponseEntity<Liga>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("liga/{id}")
	@ApiOperation(value = "Briše ligu iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	public ResponseEntity<Liga> deleteLiga(@PathVariable("id") Integer id) {
		if(ligaRepository.existsById(id)) {
			ligaRepository.deleteById(id);
			
			if(id == -100) {
				jdbcTemplate.execute("insert into liga (id, naziv, oznaka) values (-100,'test','test')");
			}
			return new ResponseEntity<Liga>(HttpStatus.OK);
		}
		return new ResponseEntity<Liga>(HttpStatus.NO_CONTENT);
	}
	
	
}
