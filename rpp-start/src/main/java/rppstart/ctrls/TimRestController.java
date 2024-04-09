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
import rppstart.jpa.Tim;
import rppstart.repositories.TimRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
@Api(tags = {"Tim CRUD operacije"})
public class TimRestController {
	
	@Autowired
	private TimRepository timRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("tim")
	@ApiOperation(value = "Vraća kolekciju svih timova iz baze podataka")
	public Collection<Tim> getTimove() {
		return timRepository.findAll();
	}

	@GetMapping("tim/{id}")
	@ApiOperation(value = "Vraća tim iz baze podataka čija je id vrednost prosleđena kao path varijabla")
	public Tim getTim(@PathVariable("id") Integer id) {
		return timRepository.getOne(id);
	}
	
	@GetMapping("timNaziv/{naziv}")
	@ApiOperation(value = "Vraća kolekciju svih timova iz baze podataka koji u nazivu sadrže string prosleđen kao path varijabla")
	public Collection<Tim> getTimById(@PathVariable("naziv") String naziv) {
		return timRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@PostMapping("tim")
	@ApiOperation(value = "Upisuje tim u bazu podataka")
	public ResponseEntity<Tim> insertTim(@RequestBody Tim tim) {
		if (!timRepository.existsById(tim.getId())) {
			timRepository.save(tim);
			return new ResponseEntity<Tim>(HttpStatus.OK);
		}
		return new ResponseEntity<Tim>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("tim")
	@ApiOperation(value = "Modifikuje postojeći tim u bazi podataka")
	public ResponseEntity<Tim> updateTim(@RequestBody Tim tim) {
		if (timRepository.existsById(tim.getId())) {
			timRepository.save(tim);
			return new ResponseEntity<Tim>(HttpStatus.OK);
		}
		return new ResponseEntity<Tim>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("tim/{id}")
	@ApiOperation(value = "Briše tim iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	public ResponseEntity<Tim> deleteTim(@PathVariable("id") Integer id) {
		if(timRepository.existsById(id)) {
			timRepository.deleteById(id);
			
			if(id == -100) {
				jdbcTemplate.execute("insert into tim (id, naziv, osnovan, sediste, liga) values (-100, 'test', to_date('28.03.2022.', 'dd.mm.yyyy.'), 'test', 2)");
			}
			return new ResponseEntity<Tim>(HttpStatus.OK);
		}
		return new ResponseEntity<Tim>(HttpStatus.NO_CONTENT);
	}

}
