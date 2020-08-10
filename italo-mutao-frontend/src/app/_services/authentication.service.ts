﻿import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { BehaviorSubject, Observable } from "rxjs";
import { map } from "rxjs/operators";

import { environment } from "@environments/environment";
import { User, ApiResponse } from "@app/_models";

@Injectable({ providedIn: "root" })
export class AuthenticationService {
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(
      JSON.parse(localStorage.getItem("currentUser"))
    );
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  login(name: string, password: string) {
    return this.http
      .post<ApiResponse<User>>(`${environment.apiUrl}/login`, {
        name,
        password,
      })
      .pipe(
        map((api: ApiResponse<User>) => {
          if (api.response && api.response.token) {
            localStorage.setItem("currentUser", JSON.stringify(api.response));
            this.currentUserSubject.next(api.response);
          }
          return api.response;
        })
      );
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.clear();
    this.currentUserSubject.next(null);
  }
}
