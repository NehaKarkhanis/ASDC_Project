package Group3.JobsMadeEasy.authentication.role.controller;


import Group3.JobsMadeEasy.authentication.role.dao.IRoleDao;
import Group3.JobsMadeEasy.authentication.role.model.Role;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
public class RoleController {
    private final Role role;
    public RoleController(IRoleDao roleDao){
        this.role = new Role(roleDao);
    }

    @PostMapping("/add_role")
    public boolean createRole(@ModelAttribute Role role) throws JobsMadeEasyException {
        return this.role.createRole(role);
    }

    @GetMapping("/get_role_by_id/{id}")
    public Optional<Role> getRoleById(@PathVariable int id) throws JobsMadeEasyException, SQLException {
        return role.getRoleById(id);
    }

    @GetMapping("/get_all_roles")
    public List<Role> getAllRoles() throws JobsMadeEasyException, SQLException {
        return role.getAllRoles();
    }

    @DeleteMapping("/delete_role_by_id/{id}")
    public boolean deleteRoleById(@PathVariable int id) throws JobsMadeEasyException, SQLException {
        return role.deleteRoleById(id);
    }

    @GetMapping("/view_all_roles")
    public String viewAllRoles(Model model){
        List<Role> roles;
        try {
            roles = getAllRoles();
        } catch (JobsMadeEasyException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("roles", roles);
        return "viewAllRoles";
    }

    @GetMapping("/view_role_by_id/{id}")
    public String viewUserById(Model model, @PathVariable int id) throws SQLException, JobsMadeEasyException {

        Optional<Role> roles = getRoleById(id);
        model.addAttribute("roles", roles);
        return "viewRoleById";
    }

    @GetMapping("/delete_role_by_id")
    public String deleteRoleByRoleId(Model model) throws JobsMadeEasyException, SQLException {
        List<Role> role = getAllRoles();
        model.addAttribute("role", role);
        return "deleteRoleById";
    }

}
