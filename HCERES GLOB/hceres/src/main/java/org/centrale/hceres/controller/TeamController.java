package org.centrale.hceres.controller;

import org.centrale.hceres.items.Team;
import org.centrale.hceres.service.TeamService;
import org.centrale.hceres.util.RequestParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(originPatterns = "*")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/Teams")
    public List<Team> getTeams() {
        return teamService.getTeams();
    }
    /**
     * create a team in database
     *
     * @return Team
     */
    @PostMapping(value = "/Team/Create")
    public Team createTeam(@RequestBody Map<String, Object> request) throws RequestParseException {
        return teamService.saveTeam(request);
    }

    /**
     * Delete - Delete a team
     *
     * @param id - The id of the team
     */
    @DeleteMapping("/Team/Delete/{id}")
    public void deleteTeam(@RequestBody @PathVariable("id") final Integer id) {
        teamService.deleteTeam(id);
    }
}
