package ru.stqa.pft.mantis.test;


import biz.futureware.mantis.rpc.soap.client.IssueData;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;
import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class SoapTests extends TestBase{
    private static int lastIssue;


    @BeforeMethod
    public void startSoapTest(){

    }



    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        testName = "testGetProjects";
        testDescription = "testGetProjects description";

        List<IssueData> issueList = app.soap().getIssueList(testName); //получаем список отчетов по testName
        if(!issueList.isEmpty()){                                      //выбираем из списка последний (пересобирается в обратном порядке)
            int i = issueList.get(0).getId().intValue();
            skipIfNotFixed(i);
        }

        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for(Project pr : projects){
            System.out.println("Name "+ pr.getName());
        }

    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        testName = "testCreateIssue";
        testDescription = "testCreateIssue description";
        List<IssueData> issueList = app.soap().getIssueList(testName);
        if(!issueList.isEmpty()){
            int i = issueList.get(0).getId().intValue();
            skipIfNotFixed(i);
        }

        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary(testName).
                withDescription(testDescription).withProject(projects.iterator().next());

        List<IssueData> list =  app.soap().getIssueList(testName);

        Issue created = app.soap().addIssue(issue);
        Assert.assertEquals(issue.getSummary(), created.getSummary());
    }





}
