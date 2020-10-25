package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

    private final ApplicationManager app;
    private final String login;
    private final String password;
    private final String serviceUrl;

    public SoapHelper(ApplicationManager app) {
         login = app.getProperty("web.adminLogin");
         password = app.getProperty("web.adminPassword");
         serviceUrl = app.getProperty("web.ServiceUrl");
         this.app = app;

    }

    public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
        MantisConnectPortType mc = getMantisConnect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible(login, password);
      return   Arrays.asList(projects).stream().map(
                (p) -> new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
    }

    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator().getMantisConnectPort(new URL(serviceUrl));
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        String[] categories = mc.mc_project_get_categories(login, password, BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueId = mc.mc_issue_add(login, password, issueData);
        IssueData createdIssueData = mc.mc_issue_get(login, password, issueId);
        return new Issue().withId(createdIssueData.getId().intValue()).withSummary(createdIssueData.getSummary())
                .withDescription(createdIssueData.getDescription()).
                        withProject(new Project().withId(createdIssueData.getProject().getId().intValue()).
                                withName(createdIssueData.getProject().getName()));
    }





   public IssueData getIssue(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        BigInteger isId = BigInteger.valueOf(issueId);
        MantisConnectPortType mc = getMantisConnect();
        IssueData issue = mc.mc_issue_get(login, password, isId);
       return issue;
   }


   public List<IssueData> getIssueList( String issueSummary) throws MalformedURLException, ServiceException, RemoteException {
        Project project = app.soap().getProjects().iterator().next();
        MantisConnectPortType mc = getMantisConnect();
       IssueData[] issues = mc.mc_project_get_issues(login, password, BigInteger.valueOf(project.getId()),BigInteger.valueOf(1),BigInteger.valueOf(10));
       List<IssueData> issueList = new ArrayList<IssueData>();
       for(IssueData isData : issues){
           String summary = isData.getSummary();
           if(summary.equals(issueSummary)) issueList.add(isData);
       }
       return issueList;

   }

}
