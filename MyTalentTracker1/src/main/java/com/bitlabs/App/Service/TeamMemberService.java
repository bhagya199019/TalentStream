package com.bitlabs.App.Service;

import com.bitlabs.App.Entity.TeamMember;

public interface TeamMemberService {

	public TeamMember addTeamMemberToRecruiter(Long recruiterId, TeamMember teamMember);
}
