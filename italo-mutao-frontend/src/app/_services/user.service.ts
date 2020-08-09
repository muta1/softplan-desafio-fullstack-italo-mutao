import { Injectable } from "@angular/core";
import {
  HttpClient,
  /*HttpHeaders,*/
  HttpErrorResponse,
  HttpHeaders,
} from "@angular/common/http";

import { environment } from "@environments/environment";
import { User, ApiResponse } from "@app/_models";
import { Observable, throwError } from "rxjs";
import { catchError, map } from "rxjs/operators";
import { AuthenticationService } from "./authentication.service";

@Injectable({ providedIn: "root" })
export class UserService {
  private method: string = "/user";
  private baseUri: string = environment.apiUrl + this.method;
  private headers = new HttpHeaders().set("Content-Type", "application/json");
  constructor(
    private http: HttpClient,
    private authenticationService: AuthenticationService
  ) {}

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
    return this.http.get<ApiResponse<User[]>>(`${this.baseUri}`);
  }

  // Get User
  getUser(id): Observable<any> {
    let url = `${this.baseUri}/read/${id}`;
    return this.http.get(url /*, { headers: this.headers }*/).pipe(
      map((res: Response) => {
        return res || {};
      }),
      catchError(this.errorMgmt)
    );
  }

  // Update User
  updateUser(data): Observable<any> {
    let url = `${this.baseUri}/update`;
    return this.http
      .put(url, data /*, { headers: this.headers }*/)
      .pipe(catchError(this.errorMgmt));
  }

  // Delete User
  deleteUser(id): Observable<any> {
    let url = `${this.baseUri}/delete/${id}`;
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
