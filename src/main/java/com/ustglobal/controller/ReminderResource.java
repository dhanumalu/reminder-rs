package com.ustglobal.controller;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ustglobal.beans.Reminder;
import com.ustglobal.repository.ReminderRepository;

@RestController
public class ReminderResource {

	@Autowired
	private ReminderRepository reminderRepository;

	@GetMapping("/reminders")
	public List<Reminder> retrieveAllReminders() {
		return reminderRepository.findAll();
	}

	@GetMapping("/reminders/{id}")
	public Reminder retrieveReminder(@PathVariable long id) {
		Optional<Reminder> reminder = reminderRepository.findById(id);

		//if (!reminder.isPresent())
		//	throw new ReminderNotFoundException("id-" + id);

		return reminder.get();
	}

	@DeleteMapping("/reminders/{id}")
	public void deleteReminder(@PathVariable long id) {
		reminderRepository.deleteById(id);
	}

	@PostMapping("/reminders")
	public ResponseEntity<Object> createReminder(@RequestBody Reminder reminder) {
		Reminder savedReminder = reminderRepository.save(reminder);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedReminder.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/reminders/{id}")
	public ResponseEntity<Object> updateReminder(@RequestBody Reminder reminder, @PathVariable long id) {

		Optional<Reminder> reminderOptional = reminderRepository.findById(id);

		if (!reminderOptional.isPresent())
			return ResponseEntity.notFound().build();

		reminder.setId(id);
		
		reminderRepository.save(reminder);

		return ResponseEntity.noContent().build();
	}
}
