package com.bitlabs.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchForaJobController {
	@Autowired
    private SearchForaJobService jobSearchService;

    @GetMapping("/applicant/searchjobbyskillname/{applicantId}/jobs/{skillName}")
    public ResponseEntity<Page<Job>> searchJobsBySkillAndApplicant(
    		@PathVariable int applicantId,
            @PathVariable String skillName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Job> jobs = jobSearchService.searchJobsBySkillAndApplicant(applicantId, skillName, pageable);

        if (jobs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(jobs);
    }
}
