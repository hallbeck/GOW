package sendRss;

import base.Base;

import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class TestSikuli extends Base{
    @DataProvider
    public Object[][] Mains() {
        return new Object[][] {//set trip to 0 if you want nothing send
                //{ 0, "main","","82","hallbeck2011@gmail.com","password" },//Catt 82
                { 0, "main","","82","stormy.rubra@mailinator.com","password" },//gerbil 8
                { 0, "main","","142","chicken.leader.142@mailinator.com","qazqazqaz" },//leader of F.C 142
                { 0, "main","","82","idiotrampagegow.idiot@mailinator.com","idiot133"},//Rampage 82

        };
    }
    @DataProvider
    public Object[][] One42() {
        return new Object[][] {
                { 0, "main","","142","chicken.leader.142@mailinator.com","qazqazqaz" },//leader of F.C 142
                { 2, "farm","wood","142","wood1.kerri@mailinator.com","qazqazqaz" },//deSpruce 142
                { 2, "farm","wood","142","wood2.kerri@mailinator.com","qazqazqaz" },//madrone 142
                { 2, "farm","stone","142","stone1.kerri@mailinator.com","qazqazqaz" },//rock steady 142
                { 2, "farm","stone","142","stone2.kerri@mailinator.com","qazqazqaz" },//flourite 142
                { 1, "farm","stone","142","rally1.new.k@mailinator.com","qazqazqaz" },//cat box 142
                { 2, "farm","food","142","food2.kerri@mailinator.com","qazqazqaz" },//sow Fields 142
                { 1, "farm","ore","142","ore1.kerri@mailinator.com","qazqazqaz" },//thisOre that 142
                { 1, "farm","ore","142","ore2.kerri@mailinator.com","qazqazqaz" },//blacksmithie 142
                { 1, "farm","ore","142","mule.142@mailinator.com","qazqazqaz" },//mr jiggs 142


        };
    }
    @DataProvider
    public Object[][] video() {
        return new Object[][] {//set trip to 0 if you want nothing send
                { 2, "farm","wood","142","wood2.kerri@mailinator.com","qazqazqaz" },//madrone 142

        };
    }
    @DataProvider
    public Object[][] Ore() {
        return new Object[][] {//set trip to 0 if you want nothing send
                { 0, "farm","ore","82","ore.rubra@mailinator.com","password" },//any dots 82
                { 2, "farm","ore","82","orewar.kerri@mailinator.com","password" },//ore supply 82
                { 2, "farm","ore","82","nom.kerri@mailinator.com","password" },//nom nom 82
                { 2, "farm","ore","82","idiotoregow.idiot@mailinator.com","idiot133" },//idiotore 82
                { 1, "farm","ore","142","ore1.kerri@mailinator.com","qazqazqaz" },//thisOre that 142
                { 1, "farm","ore","142","ore2.kerri@mailinator.com","qazqazqaz" },//blacksmithie 142
                { 1, "farm","ore","142","mule.142@mailinator.com","qazqazqaz" },//mr jiggs 142
        };
    }
    @DataProvider
    public Object[][] Wood() {
        return new Object[][] {//set trip to 0 if you want nothing send
                { 2, "farm","wood","82","idiotwoodgow.idiot@mailinator.com","idiot133"  },//idiotwood 82
                { 2, "farm","wood","82","woodfood.kerri@mailinator.com","password" },//smashmouth 82
                { 2, "farm","wood","82","woodwar.kerri@mailinator.com","password" },//woodfarm 82
                { 1, "farm","wood","0","kvk.fodder@mailinator.com","qazqazqaz" },//crikit 82
                { 1, "farm","wood","0","rubra.kvk.1@mailinator.com","qazqazqaz" },//tigger boo 82
                { 2, "farm","wood","142","wood1.kerri@mailinator.com","qazqazqaz" },//deSpruce 142
                { 2, "farm","wood","142","wood2.kerri@mailinator.com","qazqazqaz" },//madrone 142
        };
    }
    @DataProvider
    public Object[][] Stone() {
        return new Object[][] {//set trip to 0 if you want nothing send
                { 2, "farm","stone","82","idiotstonegow.idiot@mailinator.com","idiot133"  },//idiotstone 82
                { 2, "farm","stone","82","orestone.kerri@mailinator.com","password"  },//scorpions 82
                { 2, "farm","stone","82","stonewar.kerri@mailinator.com","password" },//stonecold 82
                { 1, "farm","stone","0","lord.rally.new.k@mailinator.com","qazqazqaz" },//michaelangel 82
                { 2, "farm","stone","142","stone1.kerri@mailinator.com","qazqazqaz" },//rock steady 142
                { 2, "farm","stone","142","stone2.kerri@mailinator.com","qazqazqaz" },//flourite 142
                { 1, "farm","stone","142","rally1.new.k@mailinator.com","qazqazqaz" },//cat box 142
        };
    }
    @DataProvider
    public Object[][] Food() {
        return new Object[][] {//set trip to 0 if you want nothing send
                { 1, "farm","food","0","food.rubra.kerri.1@mailinator.com","qazqazqaz" },//mess hall 82
                { 2, "farm","food","142","food2.kerri@mailinator.com","qazqazqaz" },//sow Fields 142
        };
    }
    @DataProvider
    public Object[][] FakeRally() {
        return new Object[][] {
                { "fake.rally.19.k@mailinator.com","qazqazqaz"},//
                { "fake.rally.14.rubra.k@mailinator.com","qazqazqaz"},//
                { "fake.rally.15.rubra.k@mailinator.com","qazqazqaz"},//
                { "fake.rally.16.rubra.k@mailinator.com","qazqazqaz"},//
                { "rally3.rubra.k@mailinator.com","qazqazqaz"},//
                { "rally4.rubra.k@mailinator.com","qazqazqaz"},//
                { "rally6.rubra.k@mailinator.com","qazqazqaz"},//
                { "fake.rally.146.1@mailinator.com","qazqazqaz"},//
                { "rally5.rubra.k@mailinator.com","password"},//
                { "rubra.kvk.2@mailinator.com","qazqazqaz"},//
                { "fake.rally.142.01.k@mailinator.com","qazqazqaz"},//
                { "fake.rally.142.02.k@mailinator.com","qazqazqaz"},//
                { "fake.rally.142.03.k@mailinator.com","qazqazqaz"},//
                { "fake.rally.142.04.k@mailinator.com","qazqazqaz"},//
                { "fake.rally.142.05.k@mailinator.com","qazqazqaz"},//
                { "lozza.fr@mailinator.com","qazqazqaz"},//
                { "rally1.rubra.k@mailinator.com","qazqazqaz"},//
                { "rally2.rubra.k@mailinator.com","qazqazqaz"},//
                { "rally8.rubra.k@mailinator.com","qazqazqaz"},//
                { "rally9.rubra.k@mailinator.com","qazqazqaz"},//
                { "rally7.rubra.k@mailinator.com","qazqazqaz"},//
                { "rally10.rubra.k@mailinator.com","qazqazqaz"},//
                { "rally.rubra.11.k@mailinator.com","qazqazqaz"},//
                { "fake.rally.12.rubra.k@mailinator.com","qazqazqaz"},//
                { "fake.rally.13.rubra.k@mailinator.com","qazqazqaz"},//
                { "fake.rally.17.k@mailinator.com","qazqazqaz"},//
                { "fake.rally.18.k@mailinator.com","qazqazqaz"},//
        };
    }

    @Test (dataProvider = "One42")//trip is number of trips -1 that the account can make
    public void video(Integer trip, String type, String rss, String kingdom, String login, String password) throws Exception {
        checkAppStatus();
        startup();
        waitForPhone();
        enterLogin(login);
        enterPassword(password,login);
        clickLogin(login);
        getPastSplash();
        treasureChest();
        athenaTreasureChest();
        helps();
        allianceGiftsButton();
        freeSpin();
        doQuests();
        if (trip != 0) {
            findBank();
            sendToBank(rss, trip);
        }
        helps();
        exitGOW();
        Wait(20);
    }

@Test (dataProvider = "Mains")//trip is number of trips -1 that the account can make
    public void mains(Integer trip, String type, String rss, String kingdom, String login, String password) throws Exception {
   /*     Screen s = new Screen();
        Region r = new Region(305,32,802,786);
        Region app = new Region(1280,3,635,1154);*/
    checkAppStatus();
    startup();
    waitForPhone();
    enterLogin(login);
    enterPassword(password,login);
    clickLogin(login);
    getPastSplash();
    treasureChest();
    athenaTreasureChest();
   // buildMe();
    helps();
    allianceGiftsButton();
    freeSpin();
    doQuests();
    helps();
    exitGOW();
    Wait(20);
}
    @Test (dataProvider = "Ore")//trip is number of trips -1 that the account can make
    public void oreFarm(Integer trip, String type, String rss, String kingdom, String login, String password) throws Exception {
        checkAppStatus();
        startup();
        waitForPhone();
        enterLogin(login);
        enterPassword(password,login);
        clickLogin(login);
        getPastSplash();
        treasureChest();
        athenaTreasureChest();
        helps();
        allianceGiftsButton();
        freeSpin();
        doQuests();
        buildMe();
        if (trip != 0) {
            findBank();
            sendToBank(rss, trip);
        }
        helps();
        exitGOW();
        Wait(20);
    }
    @Test (dataProvider = "Wood")//trip is number of trips -1 that the account can make
    public void woodFarm(Integer trip, String type, String rss, String kingdom, String login, String password) throws Exception {
        checkAppStatus();
        startup();
        waitForPhone();
        enterLogin(login);
        enterPassword(password,login);
        clickLogin(login);
        getPastSplash();
        treasureChest();
        athenaTreasureChest();
        helps();
        allianceGiftsButton();
        freeSpin();
        doQuests();
        buildMe();
        if (trip != 0) {
            findBank();
            sendToBank(rss, trip);
        }
        helps();
        exitGOW();
        Wait(20);
    }
    @Test (dataProvider = "Stone")//trip is number of trips -1 that the account can make
    public void stoneFarm(Integer trip, String type, String rss, String kingdom, String login, String password) throws Exception {
        checkAppStatus();
        startup();
        waitForPhone();
        enterLogin(login);
        enterPassword(password,login);
        clickLogin(login);
        getPastSplash();
        treasureChest();
        athenaTreasureChest();
        helps();
        allianceGiftsButton();
        freeSpin();
        doQuests();
        buildMe();
        if (trip != 0) {
            findBank();
            sendToBank(rss, trip);
        }
        helps();
        exitGOW();
        Wait(20);
    }
    @Test (dataProvider = "Food")//trip is number of trips -1 that the account can make
    public void foodFarm(Integer trip, String type, String rss, String kingdom, String login, String password) throws Exception {
        checkAppStatus();
        startup();
        waitForPhone();
        enterLogin(login);
        enterPassword(password,login);
        clickLogin(login);
        getPastSplash();
        treasureChest();
        athenaTreasureChest();
        helps();
        allianceGiftsButton();
        freeSpin();
        doQuests();
        buildMe();
        if (trip != 0) {
            findBank();
            sendToBank(rss, trip);
        }
        helps();
        exitGOW();
        Wait(20);
    }
    @Test (dataProvider = "FakeRally")
    public void rallyTest(String login, String password) throws Exception {
        checkAppStatus();
        startup();
        waitForPhone();
        enterLogin(login);
        enterPassword(password,login);
        clickLogin(login);
        getPastSplash();
        treasureChest();
        athenaTreasureChest();
        buildMe();
        helps();
        allianceGiftsButton();
        freeSpin();
        doQuests();
        exitGOW();
        Wait(20);
    }
    @Test
    public void waitProgram2H() throws Exception {
        Wait(7200);//2 hours
    }
    @Test
    public void waitProgram20M() throws Exception {
        Wait(1200);//20 min
    }
    @Test
    public void testing() throws Exception {
        buildMe();
    }
}
