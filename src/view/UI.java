package view;

import controller.UserController;
import model.dto.CreateUserDto;
import model.dto.UserResponseDto;
import model.dto.UserUpdateDto;
import model.entities.User;

import java.util.Collections;
import java.util.List;
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
                    List<UserResponseDto>  userResponseDtoList = controller.getAllUsers();
                    Collections.reverse(userResponseDtoList);
                    TableUI.getTableUI(userResponseDtoList);
                }
                case 2->{
                    System.out.print("[+] User name: ");
                    String userName = new Scanner(System.in).nextLine();
                    System.out.print("[+] Email: ");
                    String email = new Scanner(System.in).nextLine();
                    System.out.print("[+] Password: ");
                    String password = new Scanner(System.in).nextLine();
                    System.out.println(
                            controller.createNewUser(CreateUserDto
                                    .builder()
                                            .email(email)
                                            .userName(userName)
                                            .password(password)
                                    .build())
                    );
                }
                case 3->{
                    System.out.print("[+] Insert User UUID: ");
                    Integer rowAffected = controller
                            .deleteUserByUuid(new Scanner(System.in).nextLine());
                    if(rowAffected>0){
                        System.out.println("Deleted user successfully🔥");
                    }
                }
                case 4 ->{
                    System.out.print("[+] Insert User UUID to Update: ");
                    String uuid = new Scanner(System.in).nextLine();
                    System.out.print("[+] User new name: ");
                    String userName = new Scanner(System.in).nextLine();
                    System.out.print("[+] New Email: ");
                    String email = new Scanner(System.in).nextLine();
                    System.out.print("[+] New Password: ");
                    String password = new Scanner(System.in).nextLine();
                    System.out.println(controller
                            .updateUserByUuid(uuid, new UserUpdateDto(userName, email, password)));
                }
                default -> {
                    System.out.println("[!] Invalid option :(");
                }
            }
        }
    }
}
