package Zim.model.modelview;

import Zim.model.Applicant;
import Zim.model.ApplicantLog;
import Zim.model.Duplicate;

import java.util.List;

/**
 * Created by Laxton-Joe on 2017/2/20.
 */
public class ApplicantDetails {
    private Applicant applicant;
    private List<Duplicate> duplicates;
    private List<ApplicantLog> logs;

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public List<Duplicate> getDuplicates() {
        return duplicates;
    }

    public void setDuplicates(List<Duplicate> duplicates) {
        this.duplicates = duplicates;
    }

    public List<ApplicantLog> getLogs() {
        return logs;
    }

    public void setLogs(List<ApplicantLog> logs) {
        this.logs = logs;
    }
}
