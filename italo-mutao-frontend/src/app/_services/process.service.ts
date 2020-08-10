import { catchError, map } from "rxjs/operators";
import { Observable, throwError } from "rxjs";
import { Injectable } from "@angular/core";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";

import { environment } from "@environments/environment";
import { Process, ApiResponse } from "@app/_models";

@Injectable({ providedIn: "root" })
export class ProcessService {
  private method: string = "/process";
  private baseUri: string = environment.apiUrl + this.method;

  constructor(private http: HttpClient) {}

  // Create Process
  createProcess(user: Process): Observable<any> {
    let url = `${this.baseUri}/create`;
    console.log("(url): ", url);

    return this.http
      .post(url, user /*, { headers: this.headers }*/)
      .pipe(catchError(this.errorMgmt));
  }

  // Get all Processes
  getProcesses() {
    return this.http.get<ApiResponse<Process[]>>(`${this.baseUri}`);
  }

  // Get Process
  getProcess(id): Observable<any> {
    let url = `${this.baseUri}/read?id=${id}`;
    return this.http
      .get<ApiResponse<Process>>(url /*, { headers: this.headers }*/)
      .pipe(
        map((res) => {
          return res.response || {};
        }),
        catchError(this.errorMgmt)
      );
  }

  // Update Process
  updateProcess(data: Process): Observable<any> {
    let url = `${this.baseUri}/update`;
    return this.http
      .put(url, data /*, { headers: this.headers }*/)
      .pipe(catchError(this.errorMgmt));
  }

  // Delete Process
  deleteProcess(id): Observable<any> {
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
