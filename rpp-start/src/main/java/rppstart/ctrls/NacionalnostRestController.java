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
import rppstart.jpa.Nacionalnost;
import rppstart.repositories.NacionalnostRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
@Api(tags = {"Nacionalnost CRUD operacije"})
public class NacionalnostRestController {

	@Autowired
	private NacionalnostRepository nacionalnostRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("nacionalnost")
	@ApiOperation(value = "Vraća kolekciju svih nacionalnosti iz baze podataka")
	public Collection<Nacionalnost> getNacionalnosti() {
		return nacionalnostRepository.findAll();
	}

	@GetMapping("nacionalnost/{id}")
	@ApiOperation(value = "Vraća nacionalnost iz baze podataka čija je id vrednost prosleđena kao path varijabla")
	public Nacionalnost getNacionalnost(@PathVariable("id") Integer id) {
		return nacionalnostRepository.getOne(id);
	}
	
	@GetMapping("nacionalnostNaziv/{naziv}")
	@ApiOperation(value = "Vraća kolekciju svih nacionalnosti iz baze podataka koji u nazivu sadrže string prosleđen kao path varijabla")
	public Collection<Nacionalnost> getNacionalnostByNaziv(@PathVariable("naziv") String naziv) {
		return nacionalnostRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@PostMapping("nacionalnost")
	@ApiOperation(value = "Upisuje nacionalnost u bazu podataka")
	public ResponseEntity<Nacionalnost> insertNacionalnost(@RequestBody Nacionalnost nacionalnost) {
		if (!nacionalnostRepository.existsById(nacionalnost.getId())) {
			nacionalnostRepository.save(nacionalnost);
			return new ResponseEntity<Nacionalnost>(HttpStatus.OK);
		}
		return new ResponseEntity<Nacionalnost>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("nacionalnost")
	@ApiOperation(value = "Modifikuje postojeću nacionalnost u bazi podataka")
	public ResponseEntity<Nacionalnost> updateNacionalnost(@RequestBody Nacionalnost nacionalnost) {
		if (nacionalnostRepository.existsById(nacionalnost.getId())) {
			nacionalnostRepository.save(nacionalnost);
			return new ResponseEntity<Nacionalnost>(HttpStatus.OK);
		}
		return new ResponseEntity<Nacionalnost>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("nacionalnost/{id}")
	@ApiOperation(value = "Briše nacionalnost iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	public ResponseEntity<Nacionalnost> deleteNacionalnost(@PathVariable("id") Integer id) {
		if (nacionalnostRepository.existsById(id)) {
			nacionalnostRepository.deleteById(id);
			
			if(id == -100) {
				jdbcTemplate.execute("insert into nacionalnost (id, naziv, skracenica) values (-100,'test','test')");
			}
			return new ResponseEntity<Nacionalnost>(HttpStatus.OK);
		}
		return new ResponseEntity<Nacionalnost>(HttpStatus.NO_CONTENT);
	}

}
