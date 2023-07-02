package base;

import org.sikuli.api.*;
import org.sikuli.api.robot.Key;
import org.sikuli.api.robot.Keyboard;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopKeyboard;
import org.sikuli.api.robot.desktop.DesktopMouse;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Created by KHallbeck on 6/28/14.
 */
public class Base {


    Screen s = new Screen();
    Region r = new Region(944,4,418,759);//done
    ScreenLocation bank = new DesktopScreenLocation(1118,345);
    ScreenLocation vipNO = new DesktopScreenLocation(1294,144);
    ScreenLocation bookmarkButton = new DesktopScreenLocation(952,35);
    Region bluestacks = new Region(18,16,1077,247);//done
    Region e = new Region(35,545,164,291);
    Region drag = new Region(952,127,408,444);
    ScreenRegion topBarKingdom = new DesktopScreenRegion(952,35,221,39);
    ScreenRegion app = new DesktopScreenRegion(944,4,418,759);//done
    ScreenLocation bigX = new DesktopScreenLocation(1331,46);
    ScreenLocation loginArea = new DesktopScreenLocation(1046,263);
    ScreenLocation chest = new DesktopScreenLocation(983,500);
    ScreenLocation all = new DesktopScreenLocation(1150,150);
    ScreenLocation top = Relative.to(all).below(40).getScreenLocation();
    ScreenLocation bottom = Relative.to(all).below(400).getScreenLocation();
    ScreenLocation goLeft = Relative.to(all).below(275).left(160).getScreenLocation();
    ScreenLocation goRight = Relative.to(all).below(275).right(160).getScreenLocation();
    //ScreenRegion launchApp = new DesktopScreenRegion(1537,21,206,570);
    //ScreenRegion Login = new DesktopScreenRegion(1517,387,48,23);
    ScreenRegion quests = new DesktopScreenRegion(958,158,397,274);
    //ScreenLocation login = new DesktopScreenLocation(1517,387);
    //ScreenLocation password = new DesktopScreenLocation(1476,457);
    //ScreenLocation smallX = new DesktopScreenLocation(1829,40);
    //ScreenLocation Collect_OK = new DesktopScreenLocation(566,3);

    //target offset is r.getCenter().right(x).below(y)).
    static Mouse mouse = new DesktopMouse() ;
    Keyboard kb = new DesktopKeyboard();


    String imgRoot = "C:\\games\\GOW\\src\\test\\resources\\";
    //String appRoot = "C:\\ProgramData\\BlueStacks\\UserData\\Library\\My Apps\\";
    String appRoot = "C:\\Users\\Public\\Desktop\\";

    public void checkAppStatus()throws Exception{
        ScreenRegion phoneExists = app.find(new ImageTarget(new File(imgRoot+"phone.png")));
            if(phoneExists != null) {
                exitGOW();
                Wait(15);
            }
    }

    public void launchApp () throws Exception{
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        print("Current relative path is: " + s);
        //String appToLaunch = ("Game Of War.lnk");
        String appToLaunch = ("Start BlueStacks.lnk");
        print("Current app to open is: " + appToLaunch);
        //Runtime rt = Runtime.getRuntime();
       // Process p = rt.exec(appToLaunch);
            File file = new File(appRoot+appToLaunch);
            //first check if Desktop is supported by Platform or not
            if(!Desktop.isDesktopSupported()){
                System.out.println("Desktop is not supported");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
        print("Current app to open is: " + file);
            if(file.exists()) desktop.open(file);
        //until I get the right shortcut <sigh>
        Wait(10);
        clickGOW();
    }

    public void clickGOW() throws Exception{
        bluestacks.exists(imgRoot + "GOWicon.png",300);
        bluestacks.find(imgRoot + "GOWicon.png");
        print("waiting...");
        bluestacks.click(bluestacks.find(imgRoot + "GOWicon.png"));
        Wait(2);
    }
    public void startup()throws Exception{
        try{
            clickGOW();
        } catch (Exception e) {
            print("couldn't find the GOW icon. Bluestacks must have died");
            killBlueStacks();
            Wait(10);
            print("Killed BlueStacks. I'll wait 10 seconds before relaunching");
            launchApp();
            clickGOW();
        }
    }

public boolean doesItExist(String lookFor){
    Boolean result = true;
    try {
        if (null != r.find(lookFor)) {
            //AutoComplete
            print("true");
            result = true;
        }
    } catch(Exception e){
        print("false");
        result = false;
    }
    print ("result is: "+result);
    return result;
}
    //lots o stuff
    public void Wait(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
            //print("Waiting " + seconds + " seconds");
        } catch (Exception e) {
            print("Sleep exception...its a nightmare");
        }
    }
    public void print(String text) {
        System.out.println(text);
    }

    public void checkAppStillLoggedIn()throws Exception{
        if(!doesItExist(imgRoot+"Typeemailher.png")) {
            getPastSplash();
            exitGOW();
            Wait(15);
        }
    }
    public void enterLogin(String login) throws Exception{
        if(doesItExist(imgRoot+"Typeemailher.png")) {
            Wait(2);
            ScreenLocation f = Relative.to(loginArea).right(50).below(5).getScreenLocation();
            mouse.hover(f);
            r.doubleClick(r.find(imgRoot + "Typeemailher.png"));
            Wait(3);
            kb.type(login);
            kb.type(Key.ENTER);
            print("login typed: ");
            Wait(2);
            } else {
                checkAppStillLoggedIn();
            }
    }
    public void enterPassword(String password, String login) throws Exception{
            Wait(2);
            r.click(r.find(imgRoot + "Typepassword.png"));
            Wait(2);
            print("password: typed");
            kb.type(password);
            kb.type(Key.ENTER);
    }
    public void clickLogin(String login) throws Exception{
        if(doesItExist(imgRoot+"Typeemailher.png")) {
            enterLogin(login);
        }
        Wait(2);
        r.click(r.find(imgRoot + "Login.png"));
        email(login);
    }
    public void clickTopX()throws Exception{
        Wait(5);
        ScreenLocation f = Relative.to(bigX).right(10).getScreenLocation();
        mouse.click(f);
    }
    public void clickX()throws Exception{
        Wait(2);
        ScreenLocation e = Relative.to(bigX).left(0).below(20).getScreenLocation();
        mouse.click(e);
    }
    public void clickAnother1X()throws Exception{//not used
        Wait(5);
        ScreenLocation f = Relative.to(bigX).left(20).below(45).getScreenLocation();
        mouse.click(f);
    }
    public void clickAnotherX()throws Exception{
        Wait(5);
        ScreenLocation f = Relative.to(bigX).left(20).below(50).getScreenLocation();
        mouse.click(f);
    }
    public void clickMidX()throws Exception{
        ScreenLocation e = Relative.to(bigX).below(80).left(15).getScreenLocation();
        mouse.click(e);
    }
    public void clickLowerX()throws Exception{
        ScreenLocation e = Relative.to(bigX).below(135).left(25).getScreenLocation();
        mouse.click(e);
    }
    public void goldStore(){
        try{
            ScreenRegion goldStoreClose = app.find(new ImageTarget(new File(imgRoot+"TheGoldStore.png")));
            ScreenLocation goldStoreCloseLocation = goldStoreClose.getUpperRightCorner();
            print("looking for Gold Store close");
            mouse.click( goldStoreCloseLocation );
            Wait(1);
        }   catch (Exception e) {
            print("cant find Gold Store close");
        }
    }
    public boolean lookForGameHelp()throws Exception{
            //while game help is not there.
        //if game help IS NOT there return false
        Boolean result = true;
        try {
            if (null != r.find(imgRoot + "gameHelp.png")) {
                //AutoComplete
                print("true");
                result = true;
            }
        } catch(Exception e){
            print("false");
            result = false;
        }
        print ("result is: "+result);
        return result;

    }
    public void getPastSplash ()throws Exception{
        Wait(18);

            if (!lookForGameHelp()) {
                try {
                    Wait(2);
                    clickTopX();
                    print("clicked top x");
                } catch (Exception e) {
                    print("I dunno");
                }
            } if (!lookForGameHelp()) {
                try {
                    Wait(2);
                    clickX();
                    print("clicked next to top x");
                } catch (Exception e) {
                    print("I dunno");
                }
            } if (!lookForGameHelp()) {
                try {
                    Wait(2);
                    clickMidX();
                    print("clicked mid x");
                } catch (Exception e) {
                    print("I dunno");
                }
            } if (!lookForGameHelp()) {
                try {
                    Wait(2);
                    clickAnotherX();
                    print("clicked another x");
                } catch (Exception e) {
                    print("I dunno");
                }
            } if (!lookForGameHelp()) {
                try {
                    Wait(2);
                    clickLowerX();
                    print("clicked lower x");
                } catch (Exception e) {
                    print("I dunno");
                }
            } if (!lookForGameHelp()) {
                Wait(2);
                try {
                    goldStore();
                } catch (Exception e) {
                    print("no gold store popup");
                }
            } if (!lookForGameHelp()) {
                try {
                    r.click(r.find(imgRoot + "cornerX.png"));
                    print("clicking corner button");
                } catch (Exception e) {
                    print("no corner button");
                }
            }
    }
    public void waitForChest() throws Exception {
        r.wait(imgRoot + "Tchest.png",20);
    }
    public void waitForPhone() throws Exception {
        try {
            print("waiting for login page");
            r.wait(imgRoot + "Typeemailher.png", 300);
        }catch(Exception e){
            print("couldn't find the login. maybe I was already logged in.");
            try{
                getPastSplash();
                exitGOW();
                startup();
            }
            catch (Exception f){
                killBlueStacks();
                Wait(10);
                print("Killed BlueStacks. I waited 10 seconds before relaunching");
                startup();
            }
        }
    }
    public void treasureChest() throws Exception {
        //click treasure chest
        print("looking for treasure chest");
        ScreenLocation t = Relative.to(chest).right(20).below(5).getScreenLocation();
        mouse.click(t);
        //collect treasure chest
        Wait(2);
        try {
            r.click(r.find(imgRoot + "collect.png"));
            //System.out.println("Waiting " + seconds + " seconds");
        } catch (Exception e) {
            print("no collect button reg treasure chest");
        }
        try {
            r.click(r.find(imgRoot + "OK.png"));
        } catch (Exception e) {
            print("no OK button");
        }
    }
    public void athenaTreasureChest() throws Exception{
        print("looking for treasure chest");
        try {
            r.click(r.find(imgRoot + "athenaTC.png"));
        } catch (Exception e) {
            print("athena treasure chest");
        }
        //collect treasure chest
        Wait(3);
        try {
            r.click(r.find(imgRoot + "collect.png"));
            //System.out.println("Waiting " + seconds + " seconds");
        } catch (Exception e) {
            print("no collect button reg treasure chest");
        }
        try {
            r.click(r.find(imgRoot + "OKAthena.png"));
        } catch (Exception e) {
            print("no OK button");
        }
    }

    public void freeSpin() throws Exception {
        //Free spin chip
        //ScreenRegion casinoExists = app.find(new ImageTarget(new File(imgRoot+"casino.png")));
        //ScreenRegion freeSpin = app.find(new ImageTarget(new File(imgRoot + "FreeSpin.png")));
        //ScreenRegion chest = app.find(new ImageTarget(new File(imgRoot + "casinoChest.png")));
        //ScreenRegion collect = app.find(new ImageTarget(new File(imgRoot + "collectSpinChest.png")));
        try {
            r.click(r.find(imgRoot + "aboutGOW.png"));
            Wait(1);
            r.click(r.find(imgRoot + "aboutGOW.png"));
            print("clicked + in menu");
            Wait(2);
            r.click(r.find(imgRoot + "casino.png"));
        } catch (Exception e) {
            print("no + on screen. weird.");

        }
        try {
            Double score = r.find(imgRoot + "FreeSpin.png").getScore();
            print("score is: " + score);
            BigDecimal d1 = BigDecimal.valueOf(score);
            BigDecimal d2 = BigDecimal.valueOf(.90);

            int retval = d1.compareTo(d2);
            //String answer = Integer.toString(retval);
            if (retval > 0) {
                try {
                    r.click(r.find(imgRoot + "freeSpin.png"));
                    Wait(5);
                    try {
                        if (chest != null) {
                            r.click(r.find(imgRoot + "TreasureCasino.png"));
                            Wait(2);
                            r.click(r.find(imgRoot + "collectSpinChest.png"));
                        }
                    } catch (Exception e) {
                        print("no treasure chest");
                    }
                } catch (Exception e) {
                    print("Free spin didn't match");
                }
            } else {
                print("couldn't find free spin.");
            }
        }
        catch (Exception e){
            print("no free casino.");
        }
    }
    public void townView() throws Exception {
        //Town View
        try {
        print("looking for Townview");
        r.click(r.find(imgRoot + "town.png"));
        } catch (Exception e)  {
            print("in town view already");
        }
    }

    public void kingdomView() throws Exception {
        //To Kingdom View
        try {
            r.click(r.find(imgRoot + "kingdom.png"));
            print("clicked Kingdomview");
        } catch (Exception e)  {
            print("in kingdom view already");
            }
    }

    public void allianceGiftsButton() throws Exception {
        //Alliance button (gifts)
        try {
            r.click(r.find(imgRoot + "giftsAvailable.png"));
        //accept gifts Gift box
            r.click(r.find(imgRoot + "gifts.png"));
        //while open gift exists
            while (null != r.find(imgRoot + "open.png")) {
            //Open Gift
                r.click(r.find(imgRoot + "open.png"));
                Wait(2);//Clear Gift
                r.click(r.find(imgRoot + "clear.png"));
                Wait(2);
            }
            print("no more gifts to open");
            townView();
        } catch (Exception e) {
            print("no gifts to open");
        }
    }

    public void doQuests() throws Exception {
        //Do Quests
        clickQuests();
        clickQuests();
        allianceQuests();
        collectQuests();
        startQuests();
        //Daily Quests -
        clickQuests();
        dailyQuests();
        collectQuests();
        startQuests();
        clickQuests();
        vipQuests();
        vipNotActive();
        collectQuests();
        startQuests();
        townView();
    }
    public void clickQuests() throws Exception{
        try {
            r.click(r.find(imgRoot + "quests.png"));
            print("Clicking Quests");
        } catch (Exception e) {
            print("cant find Quests button");
        }
    }
    //Alliance Quests
    public void allianceQuests() throws Exception {
        try {
            r.click(r.find(imgRoot + "AllianceQuests.png"));
            print("Clicking Alliance Quests");
        } catch (Exception e) {
            print("cant find Alliance Quests button");
        }
    }
    //Daily Quests
    public void dailyQuests() throws Exception {
        try {
            Wait(2);
            r.click(r.find(imgRoot + "DailyQuests.png"));
            print("Clicking Daily Quests");
        } catch (Exception e) {
            print("cant find Daily Quests");
        }
    }
    //Alliance Quests
    public void vipQuests() throws Exception {
        try {
            r.click(r.find(imgRoot + "VIPQuests.png"));
            r.click(r.find(imgRoot + "VIPQuests.png"));
            print("Clicking VIP Quests");
        } catch (Exception e) {
                print("cant find VIP Quests button");
        }
    }
    public void vipNotActive() throws Exception{
        print("Is the close button there? " + doesItExist(imgRoot +"VIPNO.png"));

        while (doesItExist(imgRoot +"VIPNO.png")) {
            print("Is the close button there? " + doesItExist(imgRoot +"VIPNO.png"));
            try {
                Wait(2);
                ScreenLocation e = Relative.to(vipNO).right(11).below(17).getScreenLocation();
                mouse.hover(e);
                mouse.click(e);
                print("Is the close button there? " + doesItExist(imgRoot +"VIPNO.png"));
                Wait(2);
                //mouse.press();
            } catch (Exception e) {
                print("VIP Quests are active. go for it.");
            }
        }
        print("LASTIs the close NO VIP button there? " + doesItExist(imgRoot +"VIPNO.png"));
    }
    //Collect Quest
    public void collectQuests() throws Exception {
        try {
            ScreenRegion collectButton = quests.find(new ImageTarget(new File(imgRoot + "collectLocation.png")));
            ScreenLocation collectButtonLocation = collectButton.getCenter();
            Double score = r.find(imgRoot + "collectLocation.png").getScore();
            print("score is: " + score);
            BigDecimal d1 = BigDecimal.valueOf(score);
            BigDecimal d2 = BigDecimal.valueOf(.90);

            int retval = d1.compareTo(d2);
            //String answer = Integer.toString(retval);
            if (retval == 1.0) {
                mouse.doubleClick( collectButtonLocation );
                print("Collecting Quests from score match");
                heroUpgrade();
            }
        } catch (Exception e) {
            print("cant find collect Quests button");
        }
        try{
        //while AutoCommplete exists
            while (null != r.find(imgRoot + "AutoComplete.png")) {
            //AutoComplete
            r.click(r.find(imgRoot + "AutoComplete.png"));
                heroUpgrade();
            }
        } catch (Exception E) {
        print("no more Quests to Auto commplete");
        }
    }
    public void epicQuestStart() {
        try{
            ScreenRegion questButton = app.find(new ImageTarget(new File(imgRoot+"EpicQuest.png")));
            ScreenLocation questButtonLocation = questButton.getLowerRightCorner();
            print("looking for Start Epic Quests button");
            mouse.click( questButtonLocation );
            heroUpgrade();
            mouse.click( questButtonLocation );
            heroUpgrade();
        }   catch (Exception e) {
            print("cant find Epic Quests button");
        }
    }
    public void legendaryQuestStart() {
        try{
            ScreenRegion questButton = app.find(new ImageTarget(new File(imgRoot+"LegendaryQuest.png")));
            ScreenLocation questButtonLocation = questButton.getLowerRightCorner();
            print("looking for Start Legendary Quests button");
            mouse.click( questButtonLocation );
            heroUpgrade();
            mouse.click( questButtonLocation );
            heroUpgrade();
        }   catch (Exception e) {
            print("cant find Legendary Quests button");
        }
    }
    public void rareQuestStart() {
        try{
            ScreenRegion questButton = app.find(new ImageTarget(new File(imgRoot+"RareQuest.png")));
            ScreenLocation questButtonLocation = questButton.getLowerRightCorner();
            print("looking for Start Rare Quests button");
            mouse.click( questButtonLocation );
            heroUpgrade();
            mouse.click( questButtonLocation );
            heroUpgrade();
        }   catch (Exception e) {
            print("cant find Rare Quests button");
        }
    }
    public void commonQuestStart() {
        try{
            ScreenRegion questButton = app.find(new ImageTarget(new File(imgRoot+"commonQuest.png")));
            ScreenLocation questButtonLocation = questButton.getLowerRightCorner();
            print("looking for Start Common Quests button");
            mouse.click( questButtonLocation );
            heroUpgrade();
            mouse.click( questButtonLocation );
            heroUpgrade();
        }   catch (Exception e) {
            print("cant find Common Quests button");
        }
    }
    public void uncommonQuestStart() {
        try{
            ScreenRegion questButton = app.find(new ImageTarget(new File(imgRoot+"UncommonQuest.png")));
            ScreenLocation questButtonLocation = questButton.getLowerRightCorner();
            print("looking for Start Uncommon Quests button");
            mouse.click( questButtonLocation );
            heroUpgrade();
            mouse.click( questButtonLocation );
            heroUpgrade();
        }   catch (Exception e) {
            print("cant find Uncommon Quests button");
        }
    }
    public void basicQuestStart(){
        try{
            ScreenRegion questButton = app.find(new ImageTarget(new File(imgRoot+"basicQuest.png")));
            ScreenLocation questButtonLocation = questButton.getLowerRightCorner();
            print("looking for Start Basic Quests button");
            mouse.click( questButtonLocation );
            heroUpgrade();
            mouse.click( questButtonLocation );
            heroUpgrade();
        }   catch (Exception e) {
            print("cant find Start Basic Quests button");
        }
    }
    public void heroUpgrade(){
        try{
            r.click(r.find(imgRoot + "OKHero.png"));
        }catch(Exception f) {
            print("there is no Hero upgrade OK button");
        }
    }
    public void startQuests(){
        //Start new quest - look for Legendary,
        //Epic, Rare, uncommon, common, basic
        //rare
        try{
            legendaryQuestStart();
            epicQuestStart();
        }catch (Exception e)  {
            print("no legendary quests");
            try{
                //epicQuestStart();
            }catch (Exception f)  {
                //print("no epic quests");
                try{
                    rareQuestStart();
                }catch (Exception g)  {
                    print("no rare quests");
                    try{
                        uncommonQuestStart();
                    }catch (Exception h)  {
                        print("no uncommon quests");
                        try{
                            commonQuestStart();
                        }catch (Exception i)  {
                            print("no common quests");
                            try {
                                basicQuestStart();
                            }catch (Exception j)  {
                                print("no basic quests");
                            }
                        }
                    }
                }
            }
        }
    }
    public void helps() throws Exception {
        try {
            r.click(r.find(imgRoot + "help.png"));
            print("clicked Help2");
        } catch (Exception e) {
            print("no help button");
        }
        try {
            r.click(r.find(imgRoot + "help.png"));
            print("clicked Help2");
        } catch (Exception e) {
            print("no help button");
        }
        try {
            r.click(r.find(imgRoot + "HelpAll.png"));
            print("clicked HelpAll");
        } catch (Exception e) {
            print("no help all");
        }
    }
    public void swipeDown(){
        mouse.hover(top);
        mouse.press();
        mouse.drag(top);
        mouse.drop(bottom);
        mouse.release();
    }
    public void swipeUp(){
        mouse.hover(bottom);
        mouse.press();
        mouse.drag(bottom);
        mouse.drop(top);
        mouse.release();
    }
    public void swipeLeft(){
        mouse.hover(goRight);
        mouse.press();
        mouse.drag(goRight);
        mouse.drop(goLeft);
        mouse.release();
    }
    public void swipeRight(){
        mouse.hover(goLeft);
        mouse.press();
        mouse.drag(goLeft);
        mouse.drop(goRight);
        mouse.release();
    }
    public void buildMe()throws Exception{
        try{
            buildMeRss1();
        }
        catch(Exception e){
            print("no build available");
            try{
                buildMeRss2();
            }
            catch(Exception f){
                print("no build available");
                try{
                    buildMeFrontGate();
                }
                catch (Exception g){
                    print("no build available");
                    try{
                        buildMeRightTown();
                    }
                    catch (Exception h){
                        print("no build available");
                        try{
                            buildMeMidTown();
                        }
                        catch (Exception i){
                            print("no build available");
                            try{
                                buildMeLeftTown();
                            }
                            catch (Exception j){
                                print("no build available");
                                try{
                                    buildMeLowerLeftTown();
                                }
                                catch (Exception k){
                                    print("no build available");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    //farthest rss to the right
    public void buildMeRss1() throws Exception {
        swipeUp();
        swipeLeft();
        swipeLeft();
        swipeLeft();
        swipeLeft();
        swipeDown();
        findBuild();
    }

    //next after rss1
    public void buildMeRss2() throws Exception {
        swipeUp();
        findBuild();
    }
    public void buildMeFrontGate() throws Exception {
        swipeUp();
        swipeLeft();
        swipeLeft();
        findBuild();
    }
    public void buildMeRightTown() throws Exception {
        swipeDown();
        findBuild();
    }
    public void buildMeMidTown() throws Exception {
        swipeRight();
        findBuild();
    }
    public void buildMeLeftTown() throws Exception {
        swipeRight();
        findBuild();
    }
    public void buildMeLowerLeftTown() throws Exception {
        swipeUp();
        findBuild();
    }
    public void findBuild(){
        //I could have something where it looks for each one and if true then sets stat image to be "image"
        if(doesItExist(imgRoot + "build_stone.png")||doesItExist(imgRoot + "build_wood.png")||doesItExist(imgRoot + "build_food.png")
                ||doesItExist(imgRoot + "build_ore.png")/*||doesItExist(imgRoot + "build_wall.png")*/||doesItExist(imgRoot + "build_villa.png")
                /*||doesItExist(imgRoot + "build_forge.png")*/||doesItExist(imgRoot + "build_barracks.png")||doesItExist(imgRoot + "build_hospital.png")
                ||doesItExist(imgRoot + "build_tower.png")||doesItExist(imgRoot + "build_academy.png")||doesItExist(imgRoot + "build_storehouse.png")
                /*||doesItExist(imgRoot + "build_marketplace.png")*/) {
        try {
            try {
                r.click(r.find(imgRoot + "build_stone.png").getTarget());
            } catch(Exception e1){print("not stone may be other resource");
                try {
                    r.doubleClick(r.find(imgRoot + "build_wood.png").getTarget());
                } catch(Exception e2){print("not wood may be other resource");
                    try {
                        r.doubleClick(r.find(imgRoot + "build_food.png").getTarget());
                    } catch(Exception e3){print("not food may be other resource");
                        try {
                            r.doubleClick(r.find(imgRoot + "build_ore.png").getTarget());
                        } catch(Exception e4){print(" not ore may be other resource");
                            try {
                                //r.doubleClick(r.find(imgRoot + "build_wall.png").getTarget());
                            } catch(Exception e5){//print("no wall to upgrade");
                                try {
                                    r.doubleClick(r.find(imgRoot + "build_villa.png").getTarget());
                                } catch(Exception e6){print("no villa to upgrade");
                                    try {
                                        //r.doubleClick(r.find(imgRoot + "build_forge.png").getTarget());
                                    } catch(Exception e7){//print("no forge to upgrade");
                                        try {
                                            r.doubleClick(r.find(imgRoot + "build_barracks.png").getTarget());
                                        } catch(Exception e8){print("no barracks to upgrade");
                                            try {
                                                r.doubleClick(r.find(imgRoot + "build_hospital.png").getTarget());
                                            } catch(Exception e9){print("no hospital to upgrade");
                                                try {
                                                    r.doubleClick(r.find(imgRoot + "build_tower.png").getTarget());
                                                } catch(Exception e10){print("no tower to upgrade");
                                                    try {
                                                        r.doubleClick(r.find(imgRoot + "build_academy.png").getTarget());
                                                    } catch(Exception e11){print("no academy to upgrade");
                                                        try {
                                                            r.doubleClick(r.find(imgRoot + "build_storehouse.png").getTarget());
                                                        } catch(Exception e12){print("no storehouse to upgrade");
                                                            try {
                                                                //r.doubleClick(r.find(imgRoot + "build_marketplace.png").getTarget());
                                                            } catch(Exception e13){//print("no marketplace to upgrade");
                                                             }}}}}}}}}}}}}
            Wait(2);
            r.click(r.find(imgRoot + "upgrade1.png").getTarget());
            Wait(2);
            r.click(r.find(imgRoot + "upgrade2.png"));
            Wait(2);
            r.click(r.find(imgRoot + "getHelp.png"));
            Wait(2);
        } catch (Exception e) {
            print("thought there was an arrow");
        }
        print("no build available");
    }
    }
    public void stopRally() throws Exception {
        //To stop a rally
        r.exists(imgRoot + "march.png").click(imgRoot + "march.png");
        //cancel
        r.click(imgRoot + "cancelRally.png");
        //confirm
        r.click(imgRoot + "confirm.png");
    }

    //to set a rally - TO DO
    //click on bookmarks
    //r.click(imgRoot+"1404084281316.png");

    public void rssSendHelpButton() throws Exception {
        //Click help to send
        try {
            Wait(1);
            r.click(r.find(imgRoot + "rssHelp.png"));
            r.click(r.find(imgRoot + "rssHelp.png"));
            print("clicked Help");
        } catch (Exception e) {
            r.click(r.find(imgRoot + "rssHelpTimer.png"));
            print("for some reason there was no help");
        }
    }
    public void rssSendOKButton() throws Exception {
        ScreenRegion okPresent = app.find(new ImageTarget(new File(imgRoot+"rssOK.png")));
        try {
            Wait(1);
            r.click(r.find(imgRoot + "rssOK.png"));
            print("clicked OK");
            Wait(1);
            if(okPresent != null) {
                r.click(r.find(imgRoot + "rssOK.png"));
            }
        }catch (Exception e){
            print("for some reason there was no OK");
        }
    }
    public void bookmarks() throws Exception {
        Wait(2);
        print("1. Is the bookmark button there? " + doesItExist(imgRoot +"bookmarks.png"));
        while(!doesItExist(imgRoot +"bookmarks.png")) {
            kingdomView();
            Wait(3);
            print("waiting");
            print("1.5 Is the bookmark button there? " + doesItExist(imgRoot +"bookmarks.png"));
        }
        print("2. Is the bookmark button there? " + doesItExist(imgRoot +"bookmarks.png"));
        if (doesItExist(imgRoot +"bookmarks.png")) {
            Wait(2);
            ScreenLocation e = Relative.to(bookmarkButton).right(90).below(18).getScreenLocation();
            mouse.click(e);
            print("clicked on bookmark button");
            print("2.5 Is the bookmark button there? " + doesItExist(imgRoot +"bookmarks.png"));
        }
    }
    public void clickBookmarksFavorite() throws Exception {
        ScreenRegion favoritePresent = app.find(new ImageTarget(new File(imgRoot+"favorite.png")));
        try {
            if (favoritePresent != null) {
                r.click(r.find(imgRoot + "favorite.png"));
            }
        }catch(Exception e){
            print("Cant find favorite");
        }
    }
    public void clickBookmarksEnemy() throws Exception {
        ScreenRegion favoritePresent = app.find(new ImageTarget(new File(imgRoot+"enemy.png")));
        try {
            if (favoritePresent != null) {
                r.click(r.find(imgRoot + "enemy.png"));
            }
        }catch(Exception e) {
            print("Cant find enemy");
        }
    }
    public void clickBookmarksGoTo() throws Exception {
        //click GoTo
        Wait(2);
        try {
            r.click(r.find(imgRoot + "GoTo.png"));
            print("clicked GoTo");
            Wait(2);
        }catch(Exception e) {
            print("didnt find  goto. trying again");
            try {
                townView();
                bookmarks();
                r.click(r.find(imgRoot + "GoTo.png"));
                print("clicked GoTo");
                Wait(2);
            }catch(Exception E) {
                print("didnt find goto.");
            }
        }
    }
    public void findBank() throws Exception {
        //by bookmarks in friends
        kingdomView();
        bookmarks();
        clickBookmarksFavorite();
        clickBookmarksEnemy();
        clickBookmarksGoTo();
    }
    public void sendHelpOK() throws Exception{
        rssSendHelpButton();
        rssSendOKButton();
    }

    public void sendSilverPlus(String rss) throws Exception {
        if (rss.equals("ore")) {//send ore
            sendOre();
            sendSilver();
        }
        if (rss.equals("stone")) {//send Stone
            sendStone();
            sendSilver();
        }
        if (rss.equals("wood")) {//send wood
            sendWood();
            sendSilver();
        }
        if (rss.equals("food")) {//send Food
            sendFood();
            sendSilver();
        }
        if (rss.equals("")) {//send only silver
            sendSilver();
        }
        sendHelpOK();
    }
    public void sendStone() throws Exception {
        r.dragDrop(imgRoot + "Stone.png", imgRoot + "stoneEND.png");
    }
    public void sendWood() throws Exception {
        r.dragDrop(imgRoot + "Wood.png", imgRoot + "woodEND.png");
    }
    public void sendFood() throws Exception {
        r.dragDrop(imgRoot + "Food.png", imgRoot + "foodEND.png");
    }
    public void sendOre() throws Exception {
        r.dragDrop(imgRoot + "Ore.png", imgRoot + "oreEND.png");
    }
    public void sendSilver() throws Exception {
        //print("not sending silver for a bit.");
        r.dragDrop(imgRoot + "Silver.png", imgRoot + "silverEND.png");
    }
    public void clickBank() throws Exception {
        //click on bank
        Wait(2);
        ScreenLocation e = Relative.to(bank).right(33).below(55).getScreenLocation();
        mouse.click(e);
        mouse.click(e);
        //click resource help
        Wait(2);
        r.click(imgRoot + "ResourceHelp.png");
    }

    public void sendToBank(String rss, Integer trip) throws Exception {
        try {
            if (trip != 0) {
                clickBank();
                //always send silver
                sendSilverPlus(rss);
                //then send 1+ more load of primary rss
                if (!rss.equals("")) {
                    for (int i = 0; i < trip; i++) {
                        Wait(2);
                        clickBank();
                        if (rss.equals("ore")) {//send ore
                            sendOre();
                        }
                        if (rss.equals("stone")) {//send Stone
                            sendStone();
                        }
                        if (rss.equals("wood")) {//send wood
                            sendWood();
                        }
                        if (rss.equals("food")) {//send Food
                            sendFood();
                        }
                        sendHelpOK();
                    }
                }
            } else {
                print("don't send rss for now.");
            }
        }
        catch (Exception e){
            print("failed to get to bank");
        }
    }


    public void closeBlueStacks() throws Exception{
        r.click(r.find(imgRoot + "BlueStacksX.png"));
        print("clicked x on the BlueStacks main window");
    }
    public void killBlueStacks() throws Exception{
        String[] envar = {"VAR=path"};
        Runtime.getRuntime().exec("C:\\Program Files (x86)\\BlueStacks\\HD-Quit.exe", envar);
        print("made BlueStacks Quit");
    }
    public void exitGOW() throws Exception{
        try {
            r.click(r.find(imgRoot + "aboutGOW.png"));
            Wait(1);
            try {
                r.click(r.find(imgRoot + "aboutGOW.png"));
            }catch(Exception e){print("clicked + in menu for a second time just in case. This should fail.");}
            print("clicked + in menu");
            Wait(2);
            r.click(r.find(imgRoot + "MZLogo.png"));
            print("clicked MZLogo/accounts");
            Wait(2);
            r.click(r.find(imgRoot + "LogOut.png"));
            try {
                r.click(r.find(imgRoot + "LogOut.png"));
            }catch(Exception e){print("clicked Log Out for a second time just in case. This should fail.");}
            print("clicked Log Out");
            Wait(2);
            r.click(r.find(imgRoot + "LogoutYes.png"));
            print("clicked Yes Log out");
            Wait(2);
        } catch (Exception E) {
            print("something went wrong logging out.");
        }
    }
    //String subject, String message
    public void email(String login)throws Exception{
        String subject = login + " just started";
        String message = login + " has begun running. Catt";
        sendMeAnEmail(subject, message);
    }
    public void sendMeAnEmail(String subject, String message)throws Exception{
        Properties emailProperties;
        Session mailSession;
        MimeMessage emailMessage;
        //JavaEmail javaEmail = new JavaEmail();

        //javaEmail.setMailServerProperties();
        String emailPort = "587";//gmail's smtp port
        emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", emailPort);
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");

        //javaEmail.createEmailMessage();
        String[] toEmails = { "hallbeck2011@gmail.com" };
        String emailSubject = subject;
        String emailBody = message;

        mailSession = Session.getDefaultInstance(emailProperties, null);
        emailMessage = new MimeMessage(mailSession);

        for (int i = 0; i < toEmails.length; i++) {
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
        }

        emailMessage.setSubject(emailSubject);
        emailMessage.setContent(emailBody, "text/html");//for a html email
        //emailMessage.setText(emailBody);// for a text email

       // javaEmail.sendEmail();
        String emailHost = "smtp.gmail.com";
        String fromUser = "hallbeck2011";//just the id alone without @gmail.com
        String fromUserEmailPassword = "sunnydays2";

        Transport transport = mailSession.getTransport("smtp");

        transport.connect(emailHost, fromUser, fromUserEmailPassword);
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
        System.out.println("Email sent successfully.");
    }

}
