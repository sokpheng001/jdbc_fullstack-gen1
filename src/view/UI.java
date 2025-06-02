package view;

import controller.UserController;
import model.dto.CreateUserDto;
import model.dto.UserUpdateDto;
import model.entities.User;

import java.util.Scanner;
import java.util.UUID;

public class UI {
    private final static UserController controller
             = new UserController();
    private static void thumbnail(){
        System.out.println("""
                =========================
                        User System
                =========================
                1. Display All User
                2. Add new User
                3. Delete User by UUID
                4. Update User by UUID
                5. Exit
                -----""");
    }
    public static void home(){
        while (true){
            thumbnail();
            System.out.print("[+] Insert option: ");
            switch (new Scanner(System.in).nextInt()){
                case 1->{
                    System.out.println(controller.getAllUsers());
                }
                case 2->{
                    System.out.println(
                            controller.createNewUser(CreateUserDto
                                    .builder()
                                            .email("van-kandal@gmail.com")
                                            .userName("dara-meanchey")
                                            .password("chanim123")
                                    .build())
                    );
                }
                case 3->{
                    System.out.print("[+] Insert User UUID: ");
                    System.out.println(controller
                            .deleteUserByUuid(new Scanner(System.in).nextLine()));
                }
                case 4 ->{
                    System.out.print("[+] Insert User UUID: ");
                    String uuid = new Scanner(System.in).nextLine();
                    System.out.println(controller
                            .updateUserByUuid(uuid, new UserUpdateDto("jame","jame123@gmail.com","123")));
                }
                default -> {
                    System.out.println("[!] Invalid option :(");
                }
            }
        }
    }
}
