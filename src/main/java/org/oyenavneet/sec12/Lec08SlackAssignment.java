package org.oyenavneet.sec12;

import org.oyenavneet.common.Utils;
import org.oyenavneet.sec12.assignment.SlackMember;
import org.oyenavneet.sec12.assignment.SlackRoom;

public class Lec08SlackAssignment {

    public static void main(String[] args) {

        var room = new SlackRoom("reactor");
        var nav = new SlackMember("nav");
        var sw = new SlackMember("sw");
        var sri = new SlackMember("sri");

        //adding 2 members
        room.addMember(nav);
        room.addMember(sw);

        nav.says("Hi all ..");
        Utils.sleepSeconds(4);

        sw.says("Hey!");
        nav.says("Want to say something");
        Utils.sleepSeconds(4);

        room.addMember(sri);
        sri.says("Hey everyone.. sri this side");

    }
}
