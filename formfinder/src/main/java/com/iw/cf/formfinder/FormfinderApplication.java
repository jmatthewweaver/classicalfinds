package com.iw.cf.formfinder;

import com.iw.cf.core.dto.Form;
import com.iw.cf.core.dto.Work;
import com.iw.cf.core.service.FormService;
import com.iw.cf.core.service.WorkService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication(scanBasePackages = {"com.iw.cf"})
@Log
public class FormfinderApplication
implements CommandLineRunner {

	private static final String[] FORMS = new String[] {
			"Concerto",
			"Symphony",
			"Overture",
			"Sonata",
			"Ballad",
			"Etude",
			"Nocturne",
			"Dance",
			"Toccata",
			"String Quartet",
			"Fugue",
			"Other"
	};

	private static final String[] CONCERTO_INSTRUMENTS = new String[] {
			"Violin",
			"Viola",
			"Trumpet",
			"Piano",
			"Clarinet",
			"Guitar",
			"Oboe",
			"Organ",
			"Saxophone",
			"Recorder",
			"Cello"
	};

	@Autowired
	private WorkService workService;

	@Autowired
	private FormService formService;

	public static void main(String[] args) {
		SpringApplication.run(FormfinderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		workService.clearForms();
		formService.deleteAll();
		Map<String, Form> formsByName = new HashMap<>();

		for(String formName: FORMS) {
			Form form = new Form();
			form.setName(formName);
			formService.insert(form);
			formsByName.put(formName, form);

			if("Concerto".equals(formName)) {
				for(String instrument: CONCERTO_INSTRUMENTS) {
					Form concertoForm = new Form();
					concertoForm.setName(instrument + " Concerto");
					formService.insert(concertoForm);
					formsByName.put(concertoForm.getName(), concertoForm);
				}
			}
		}

		List<Work> works = workService.getAll();
		for(Work work: works) {
			String foundForm = null;

			for(String formName: FORMS) {
				if("Other".equals(formName)) {
					continue;
				}

				if(foundForm != null) {
					break;
				}

				if(work.getTitle().toUpperCase().contains(formName.toUpperCase()) ||
						("Symphony".equals(formName) &&
								(work.getTitle().toUpperCase().contains("SYMPHON") ||
										work.getTitle().toUpperCase().contains("SINFON")))) {
					foundForm = formName;
				}

				if("Concerto".equals(foundForm)) {
					int minInstrumentIdx = Integer.MAX_VALUE;
					String minInstrument = null;
					for(String instrumentName: CONCERTO_INSTRUMENTS) {
						int idx = work.getTitle().indexOf(instrumentName);
						if(idx != -1 && idx < minInstrumentIdx) {
							minInstrumentIdx = idx;
							minInstrument = instrumentName;
						}
					}

					if(minInstrument != null) {
						foundForm = minInstrument + " " + foundForm;
					}
				}
			}

			if(foundForm == null) {
				foundForm = "Other";
			}
			log.info(work.getTitle() + " -> " + foundForm);
			work.setFormId(formsByName.get(foundForm).getId());
			workService.update(work);
		}
	}
}
