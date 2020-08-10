import { Injectable } from "@angular/core";
import {
  HttpClient,
  /*HttpHeaders,*/
  HttpErrorResponse,
  HttpHeaders,
} from "@angular/common/http";

import { environment } from "@environments/environment";
import { User, ApiResponse, Role, TechnicalOpinion } from "@app/_models";
import { Observable, throwError } from "rxjs";
import { catchError, map } from "rxjs/operators";
import { AuthenticationService } from "./authentication.service";

@Injectable({ providedIn: "root" })
export class UserService {
  private method: string = "/user";
  private baseUri: string = environment.apiUrl + this.method;
  private headers = new HttpHeaders().set("Content-Type", "application/json");
  constructor(private http: HttpClient, private auth: AuthenticationService) {}

  // Create
  createUser(user: User): Observable<any> {
    let url = `${this.baseUri}/create`;
    console.log("(url): ", url);

    return this.http
      .post(url, user /*, { headers: this.headers }*/)
      .pipe(catchError(this.errorMgmt));
  }

  // Get all Users
  getUsers() {
    return this.http.get<ApiResponse<User[]>>(`${this.baseUri}`).pipe(
      map((data) => {
        let users = data.response.filter(
          (user) => user.id != this.auth.currentUserValue.id
        );
        console.log("data={}}}", data);
        return users || [];
      })
    );
  }

  // Get Finisher Users
  getOtherFinisherUsers(techOpinionArr: TechnicalOpinion[]) {
    return this.http.get<User[]>(`${this.baseUri}`).pipe(
      map((users: any) => {
        if (users.response) {
          const alreadyInProcessUsers: User[] = techOpinionArr.map(
            (to) => to.user
          );
          const allFinishers: User[] = users.response.filter(
            (user) => user.role === Role.Finisher
          );
          const otherFinisherUsers: User[] = allFinishers.filter(
            (e) => !alreadyInProcessUsers.some((user) => user.name === e.name)
          );

          return otherFinisherUsers;
        } else {
          return [];
        }
      }),
      catchError(this.errorMgmt)
    );
  }

  // Get User
  getUser(id): Observable<any> {
    let url = `${this.baseUri}/read?id=${id}`;
    return this.http
      .get<ApiResponse<User>>(url /*, { headers: this.headers }*/)
      .pipe(
        map((res) => {
          return res.response || {};
        }),
        catchError(this.errorMgmt)
      );
  }

  // Update User
  updateUser(data: User): Observable<any> {
    let url = `${this.baseUri}/update`;
    return this.http
      .put(url, data /*, { headers: this.headers }*/)
      .pipe(catchError(this.errorMgmt));
  }

  // Delete User
  deleteUser(id): Observable<any> {
    console.log("id: ", id);
    let url = `${this.baseUri}/delete?id=${id}`;
    return this.http
      .delete(url /*, { headers: this.headers }*/)
      .pipe(catchError(this.errorMgmt));
  }

  // Error handling
  errorMgmt(error: HttpErrorResponse) {
    let errorMessage = "";
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  }
}
