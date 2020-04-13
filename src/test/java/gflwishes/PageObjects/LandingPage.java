package gflwishes.PageObjects;

import gflwishes.base.EnhancedBaseClass;
import gflwishes.base.Generics;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends EnhancedBaseClass {

    private Generics generics;

    public LandingPage(WebDriver baseDriver) {
        PageFactory.initElements(baseDriver, this);
        generics = new Generics(baseDriver);
        log4j = Logger.getLogger("LandingPage");
    }

    @FindBy(xpath = "//i[text()='power_settings_new']")
    public WebElement logoutbutton;

    @FindBy(xpath = "//header//p[text()='Logout']")
    public WebElement btnLogout;

    @FindBy(xpath = "//div[contains(text(),'Yard Management')]")
    public WebElement btnYardManagement;

    @FindBy(xpath = "//div[contains(text(),'Field Management')]")
    public WebElement btnFieldManagement;

    @FindBy(xpath = "//div[contains(text(),'Equipment Management')]")
    public WebElement btnEquipmentManagement;

    @FindBy(xpath = "//div[contains(text(),'Project Management')]")
    public WebElement btnProjectManagement;

    @FindBy(xpath = "//li/a[text()='Requisition Processing']")
    public WebElement btnRequisitionProcessing;

    @FindBy(xpath = "//li/a[text()='Requisitions']")
    public WebElement btnRequisition;

    @FindBy(xpath = "//li/a[text()='Logistics']")
    public WebElement btnLogistics;

    @FindBy(xpath = "//li/a[text()='Projects']")
    public WebElement btnProjects;

    @FindBy(xpath = "//li/a[text()='PO Requisitions']")
    public WebElement btnPORequisition;

    @FindBy(xpath = "//li/a[text()='PO Receiving and Cancelling']")
    public WebElement lnkPoReceiving;

    @FindBy(xpath = "//li/a[text()='Daily Work Reports']")
    public WebElement btnDailyWorkReports;

    @FindBy(xpath = "//li/a[text()='Work Order']")
    public WebElement WorkOrder;

    @FindBy(xpath = "//div[contains(text(),'Procurement')]")
    public WebElement lnkProcurement;

    @FindBy(xpath = "//div[contains(text(),'Yard Management')]")
    public WebElement lnkYardManageement;

    @FindBy(xpath = "//div[contains(text(),'Inventory Management')]")
    public WebElement InventoryMangement;

    @FindBy(xpath = "//div[contains(text(),'Operations')]")
    public WebElement Operations;

    @FindBy(xpath = "//a[contains(text(),'Inventory')]")
    public WebElement Inventory;

    @FindBy(xpath = "//a[contains(text(),'Employee Dispatcher')]")
    public WebElement EmployeeDispatcher;

    @FindBy(xpath = "//a[text()='Phase Code']")
    WebElement pp;

    @FindBy(xpath = "//a[text()='Owned Equipment']")
    WebElement lnkOwnEquipment;


    @FindBy(xpath = "//a[text()='Rental Equipment']")
    WebElement lnkRentalEquipment;

    @FindBy(xpath = "//div[contains(text(),'Employee Management')]")
    public WebElement EmployeeManagement;

    @FindBy(xpath = "//div[contains(text(),'CUSTOMERS')]")
    public WebElement Customer;
    
    @FindBy(xpath = "//div[contains(text(),'PROSPECTS ')]")
    public WebElement Prospect;
    
    @FindBy(xpath = "//div[contains(text(),'AGREEMENTS ')]")
    public WebElement Agreements;
    
    @FindBy(xpath = "//div[contains(text(),'CDE DASHBOARD ')]")
    public WebElement CDEdashboard;

    @FindBy(xpath = "(//div[contains(text(),'SERVICE ORDERS')])[1]/i")
    public WebElement ServiceOrder;

    @FindBy(xpath = "//a[contains(text(),'Security')]")
    public WebElement Security;

    @FindBy(xpath = "//a[contains(text(),'Security')]/parent::li/preceding-sibling::li/a[text()='Contracts']")
    public WebElement Contracts;

    @FindBy(xpath = "//a[contains(text(),'Site Visits')]")
    public WebElement SiteVisits;

    @FindBy(xpath = "//a[contains(text(),'Call List')]")
    public WebElement CallList;


    @FindBy(xpath = "//a[text()='Employees']")
    WebElement lnkEmployees;

    @FindBy(xpath = "//a[text()='Change Order']")
    WebElement lnkChangeOrder;

    @FindBy(xpath = "//a[text()='Project Credits']")
    WebElement lnkProjectCredits;


    public void OpenEmployeeManagement() {
        generics.pause(2);
        generics.clickOn(EmployeeManagement);
        testStepsLog("Click on Employee Management menu ");
    }

    public void OpenCustomer() {
        generics.pause(1);
        generics.clickOn(Customer);
        testStepsLog("Click on Customer link ");
        generics.pause(2);
    }

    public void OpenProspect() {
        generics.pause(1);
        generics.clickOn(Prospect);
        testStepsLog("Click on Prospect link ");
        generics.pause(2);
    }

    public void OpenAgreements() {
        generics.pause(1);
        generics.clickOn(Agreements);
        testStepsLog("Click on Agreements link ");
        generics.pause(2);
    }
    
    public void OpenCDEDashboard() {
        generics.pause(1);
        generics.clickOn(CDEdashboard);
        testStepsLog("Click on CDE Dashboard link ");
        generics.pause(5);
    }

    public void OpenServiceOrder() {
        generics.pause(1);

        generics.clickOn(ServiceOrder);
        testStepsLog("Click on ServiceOrder link ");
        generics.pause(2);
    }


    public void OpenSecurity() {
        generics.pause(1);
        generics.clickOn(Security);
        testStepsLog("Click on Security menu");
    }

    public void OpenAdminContract() {
        generics.pause(1);
        generics.clickOn(Contracts);
        testStepsLog("Click on Contracts menu");
    }

    public void OpenPMChangeOrder() {
        generics.pause(1);
        generics.clickOn(lnkChangeOrder);
        testStepsLog("Click on Change Order menu");
    }

    public void OpenProjectCredits() {
        generics.pause(1);
        generics.clickOn(lnkProjectCredits);
        testStepsLog("Click on Project Credits menu");
    }

    public void OpenSiteVisit() {
        generics.pause(1);
        generics.clickOn(SiteVisits);
        testStepsLog("Click on SiteVisit menu");
    }

    public void OpenCallList() {
        generics.pause(2);
        generics.clickOn(CallList);
        testStepsLog("Click on Call List menu");
    }

    public void OpenEmployees() {
        generics.pause(1);
        generics.clickOn(lnkEmployees);
        testStepsLog("Click on Employees links");
    }


    public void openYardManagement() {
        generics.pause(1);
        try {
            generics.clickOn(btnYardManagement);
            if (!generics.getAttribute(btnYardManagement, "class").contains("Mui-expanded")) {
                generics.clickOn(btnYardManagement);
            }
        } catch (Exception e) {
            if (!generics.getAttribute(btnYardManagement, "class").contains("Mui-expanded")) {
                generics.clickOn(btnYardManagement);
            }
        }
        testStepsLog("Click on Yard Management menu");
    }

    public void openEquipmentManagement() {
        generics.pause(1);
        generics.clickOn(btnEquipmentManagement);
        testStepsLog("Click on Equipment Management menu");
    }

    public void openOwnedEquipment() {
        generics.pause(1);
        generics.clickOn(lnkOwnEquipment);
        testStepsLog("Click on Owned Equipment menu");
    }

    public void openRentalEquipment() {
        generics.pause(1);
        generics.clickOn(lnkRentalEquipment);
        testStepsLog("Click on Owned Equipment menu");
    }

    public boolean isUserLoginSuccessful() {
        return generics.isPresent(logoutbutton);
    }

    public void openRequisitionProcessing() {
        generics.clickOn(btnRequisitionProcessing);
        testStepsLog("Click on Requisition Processing menu");
    }

    public void openRequisition() {
        generics.clickOn(btnRequisition);
        testStepsLog("Click on Requisition menu");
    }

    public void openLogistics() {
        generics.clickOn(btnLogistics);
        testStepsLog("Click on Logistics menu");
    }

    public void openProjects() {
        generics.clickOn(btnProjects);
        testStepsLog("Click on Projects menu");
    }

    public void openProcurement() {
        generics.clickOn(lnkProcurement);
        testStepsLog("Click on Procurement menu");
    }


    public void openDWR() {
        generics.clickOn(btnDailyWorkReports);
        testStepsLog("Click on Daily work report menu");
    }

    public void openPORequistion() {
        generics.clickOn(btnPORequisition);
        testStepsLog("Click on PO Requisition menu");
    }

    public void openPOReceiving() {
        generics.clickOn(lnkPoReceiving);
        testStepsLog("Click on PO Receiving and cancelling menu");
    }

    public void openFieldManagement() {
        generics.pause(1);
        try {
            generics.clickOn(btnFieldManagement);
            if (!generics.getAttribute(btnFieldManagement, "class").contains("Mui-expanded")) {
                generics.clickOn(btnFieldManagement);
            }
        } catch (Exception e) {
            if (!generics.getAttribute(btnFieldManagement, "class").contains("Mui-expanded")) {
                generics.clickOn(btnFieldManagement);
            }
        }
        testStepsLog("Click on Field Management menu");
    }

    public void openInventoryManagement() {
        generics.clickOn(InventoryMangement);
        testStepsLog("Click on Inventory Management menu");
        generics.pause(1);
    }

    public void OpenOperations() {
        generics.clickOn(Operations);
        testStepsLog("Click on Operartions menu");
        generics.pause(1);
    }

    public void ClickOnInventory() {
        generics.clickOn(Inventory);
        testStepsLog("Click on Inventory menu");
        generics.pause(10);
    }

    public void ClickOnEmployeeDispatcher() {
        generics.clickOn(EmployeeDispatcher);
        testStepsLog("Click on Employee Dispatcher menu");
        generics.pause(2);
    }

    public void ClickOnWorkOrder() {
        generics.clickOn(WorkOrder);
        testStepsLog("Click on WorkOrder menu");
        generics.pause(2);
    }

    public void openProjectManagement() {
        generics.clickOn(btnProjectManagement);
        testStepsLog("Click on Project Management menu");
    }


    public void clickpp() {
        generics.clickOn(pp);
        testStepsLog("Clicked on Project Phase");
        generics.pause(2);
    }

    @FindBy(xpath = "//a[text()='Contracts']")
    WebElement ct;

    public void clickct() {
        generics.clickOn(ct);
        testStepsLog("Clicked on Contract");
        generics.pause(2);
    }

    public void openContract() {
        generics.clickOn(ct);
        testStepsLog("Clicked on Contract");
        generics.pause(2);
    }
}