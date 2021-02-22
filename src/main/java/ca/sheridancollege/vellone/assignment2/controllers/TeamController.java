package ca.sheridancollege.vellone.assignment2.controllers;


import ca.sheridancollege.vellone.assignment2.database.DatabaseAccess;
import ca.sheridancollege.vellone.assignment2.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TeamController {

    @Autowired
    private DatabaseAccess da;
    ModelAndView mv;


    @RequestMapping("/")
    public String index(){ return "index"; }


    @RequestMapping("/index")// routs to index
    public String output(){
        return "index";
    }


    @RequestMapping("/display")
    public ModelAndView display(Model model){
        mv = new ModelAndView("display", "teams",da.getTeams());
        mv.addObject("team", new Team());
        return mv;
    }


    @PostMapping("/addTeam")
    public ModelAndView addTeam(Model model){
        mv = new ModelAndView("addTeam", "teams",da.getTeams());
        mv.addObject("team", new Team());
        return mv;
    }
    @RequestMapping("/insertTeam")
    public ModelAndView  addTeam(@ModelAttribute Team team){
        da.insertTeam(team.getTeamName(), team.getContinent(), team.getGamesPlayed(),team.getGamesWon(),team.getGamesDrawn(), team.getGamesLost(), team.getPoints());
        mv = new ModelAndView("addTeam", "teams",da.getTeams());
        return mv;
    }

    @PostMapping("/deleteTeam")
    public ModelAndView  deleteStudent(@ModelAttribute Team team){
        mv = new ModelAndView("deleteTeam", "teams",da.getTeams());
        mv.addObject("team", new Team());
        return mv;
    }
    @RequestMapping("/deleteTeamById/{id}")//id is not a hard coded value, it a variable
    public ModelAndView  deleteStudent(@PathVariable Long id){
        da.deleteTeamByID(id);
        mv = new ModelAndView("deleteTeam","teams",da.getTeams());
        return mv;
    }


    @PostMapping("/editTeam")
    public ModelAndView  editStudent(@ModelAttribute Team team){
        mv = new ModelAndView("editTeam", "teams",da.getTeams());
        mv.addObject("team", new Team());
        return mv;
    }
    @RequestMapping("/editTeamById/{id}")//{id} is not a hard coded value, it a variable
    public ModelAndView  editTeam(@PathVariable Long id){
        Team team = da.getTeam(id).get(0);//use id to get team
        mv = new ModelAndView("update","teams",da.getTeams());
        mv.addObject("team", team);
        return mv;
    }


    @PostMapping("/updateTeam")//action of form
    public ModelAndView  updateTeam(@ModelAttribute Team team){//gets team
        da.editTeamByID(team);
        mv = new ModelAndView("redirect:/","teams",da.getTeams());
        return mv;
    }
}
