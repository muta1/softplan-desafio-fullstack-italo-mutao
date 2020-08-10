import { RoleTranslator } from "./../_models/role";
import { Component } from "@angular/core";
import { first } from "rxjs/operators";

import { User } from "@app/_models";
import { UserService, AuthenticationService } from "@app/_services";

@Component({ templateUrl: "home.component.html" })
export class HomeComponent {
  loading = false;
  currentUser: User;
  userFromApi: User;

  users: User[];

  translatedRole: string;

  constructor(
    private userService: UserService,
    private authenticationService: AuthenticationService
  ) {
    this.currentUser = this.authenticationService.currentUserValue;
    this.translatedRole = RoleTranslator.translate(
      this.authenticationService.currentUserValue.role
    );
  }

  ngOnInit() {
    this.loading = true;
    // this.userService.getById(this.currentUser.id).pipe(first()).subscribe(user => {
    //     this.loading = false;
    //     this.userFromApi = user;
    // });
    this.userService
      .getUsers()
      .pipe(first())
      .subscribe((api) => {
        this.loading = false;
        this.users = api;
        console.log(this.users);

        // this.userFromApi = user;
      });
  }
}
