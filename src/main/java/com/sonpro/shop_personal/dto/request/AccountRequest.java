package com.sonpro.shop_personal.dto.request;

import com.sonpro.shop_personal.entities.Contact;
import com.sonpro.shop_personal.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class AccountRequest implements Serializable {

    @NotBlank(message = "Username không được để trống !")
    @Size(message = "Username phải có độ dài từ 8 đến 32 ký tự !", min = 8, max = 32)
    String username;
    @Email(message = "Không đúng định dạng email !")
    @NotBlank(message = "Email không được để trống !")
    String email;
    @Size(message = "Password phải có độ dài từ 8 đến 32 ký tự !", min = 8, max = 32)
    @NotBlank(message = "Password không được để trống !")
    String password;

    Contact contact;

//    @NotNull(message = "Role không được để trống !")
    List<Role> roles;

}
