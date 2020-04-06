package com.tencent.avengong.designpattern.design.structuretype.compose;

import com.tencent.avengong.designpattern.design.structuretype.compose.company.Department;
import com.tencent.avengong.designpattern.design.structuretype.compose.company.Emplyoee;

public class ComposeDemo {

    public static void main(String[] args) {

        /**
         * * /
         * * /wz/
         * * /wz/a.txt
         * * /wz/b.txt
         * * /wz/movies/
         * * /wz/movies/c.avi
         * * /xzg/
         * * /xzg/docs/
         * * /xzg/docs/d.txt
         */

        DirectoryNode fileTree = new DirectoryNode("/");
        DirectoryNode wz_node = new DirectoryNode("/wz/");
//        DirectoryNode directoryNode = new DirectoryNode("/wz/a.txt");
        FileNode a_text = new FileNode("/wz/a.txt");
        FileNode b_text = new FileNode("/wz/b.txt");
        DirectoryNode wz_move_node = new DirectoryNode("/wz/movies/");
        wz_node.addNode(a_text);
        wz_node.addNode(b_text);
        wz_node.addNode(wz_move_node);

        FileNode c_avi = new FileNode("/wz/movies/c.avi");
        wz_move_node.addNode(c_avi);


        //comopay

        Emplyoee boss = new Emplyoee(1, 50000);
        Department baseDepart = new Department(100);
        Department subDepart = new Department(200);
        subDepart.addHuman(new Emplyoee(2, 1000));
        subDepart.addHuman(new Emplyoee(3, 1000));
        subDepart.addHuman(new Emplyoee(4, 1000));
        baseDepart.addHuman(boss);
        baseDepart.addHuman(subDepart);

        long salary = baseDepart.getSalary();
        System.out.println("salary: " + salary);


    }

}
