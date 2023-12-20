package com.bitlabs.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bitlabs.App.Entity.TeamMember;
import com.bitlabs.App.Service.TeamMemberService;

@RestController
public class TeamMemberController {
	
	
	@Autowired
    private TeamMemberService teamMemberService;

    @PostMapping("/addTeam-members/{recruiterId}")
    public ResponseEntity<TeamMember> addTeamMemberToRecruiter(@PathVariable Long recruiterId, @RequestBody TeamMember teamMember
    ) {
        TeamMember savedTeamMember = teamMemberService.addTeamMemberToRecruiter(recruiterId, teamMember);

        if (savedTeamMember == null) {
            
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

      
        return new ResponseEntity<>(savedTeamMember, HttpStatus.CREATED);
    }

}
