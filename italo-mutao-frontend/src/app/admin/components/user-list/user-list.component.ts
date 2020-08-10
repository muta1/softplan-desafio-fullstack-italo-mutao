import { Component, OnInit } from "@angular/core";
import { UserService } from "@app/_services";

@Component({
  selector: "app-user-list",
  templateUrl: "./user-list.component.html",
})
export class UserListComponent implements OnInit {
  users: any = [];

  constructor(private apiService: UserService) {
    this.readUser();
  }

  ngOnInit() {}

  readUser() {
    this.apiService.getUsers().subscribe((data) => {
      this.users = data.response;
    });
  }

  removeUser(user, index) {
    if (window.confirm("Are you sure?")) {
      this.apiService.deleteUser(user).subscribe((_) => {
        console.log("user deleted");
        console.log(user);
        this.users.splice(index, 1);
      });
    }
  }
}
