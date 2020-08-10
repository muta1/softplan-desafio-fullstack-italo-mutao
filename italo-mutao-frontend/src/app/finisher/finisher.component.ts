import { Component, OnInit } from "@angular/core";
import { first } from "rxjs/operators";

import { User } from "@app/_models";
import { UserService } from "@app/_services";

@Component({ templateUrl: "finisher.component.html" })
export class FinisherComponent implements OnInit {
  loading = false;
  users: User[] = [];

  constructor(private userService: UserService) {}

  ngOnInit() {
    this.loading = true;
    this.userService
      .getUsers()
      .pipe(first())
      .subscribe((api) => {
        this.loading = false;
        this.users = api;
      });
  }
}
