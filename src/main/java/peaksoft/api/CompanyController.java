package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Company;
import peaksoft.service.CompanyService;

@Controller
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/allCompanies")
    public String getAllCompanies (Model model) {
        model.addAttribute("companies", companyService.getAllCompanies());
        return "company/companies";
    }

    @GetMapping("/getCompanyById/{id}")
    public String getCompanyById(@PathVariable Long id, Model model) {
        model.addAttribute("company", companyService.getCompanyById(id));
        return "redirect:/allCompanies";
    }

    @GetMapping("/new")
    public String addCompany(Model model){
        model.addAttribute("company", new Company());
        return "company/addCompanies";
    }
    @PostMapping("/save")
    public String saveCompany(@ModelAttribute("company") Company company){
        companyService.addCompany(company);
        return "redirect:/allCompanies";
    }
    @GetMapping("updateCompany")
    public String updateCompany(@RequestParam("companyId") Long id, Model model) {
        Company company = companyService.getCompanyById(id);
        model.addAttribute("company", company);
        return "company/updateCompany";
    }
    @PostMapping("/updateCompany")
    public String saveUpdateCompany(@ModelAttribute("company") Company company){
        companyService.updateCompany(company);
        return "redirect:/allCompanies";
    }
    @RequestMapping("deleteCompany")
    public String deleteCompany(@RequestParam("companyId") Long id){
        companyService.deleteByIdCompany(companyService.getCompanyById(id).getId());
        return "redirect:/allCompanies";
    }


}
