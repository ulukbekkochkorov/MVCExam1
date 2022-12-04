package peaksoft.service;

import peaksoft.model.Company;

import java.util.List;

public interface CompanyService {
    public List<Company> getAllCompanies();
    void addCompany(Company company);
    Company getCompanyById(Long id);
    void updateCompany(Company company);
    void deleteByIdCompany(Long id);
}

