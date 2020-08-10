import { catchError, map } from "rxjs/operators";
import { Observable, throwError } from "rxjs";
import { Injectable } from "@angular/core";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { TechnicalOpinion, Process, ApiResponse, User } from "@app/_models";
import { environment } from "@environments/environment";
@Injectable({ providedIn: "root" })
export class TechnicalOpinionService {
  private method: string = "/technical-opinion";
  private baseUri: string = environment.apiUrl + this.method;

  constructor(private http: HttpClient) {}

  // Create TechnicalOpinion
  createTechnicalOpinion(technicalOpinion: TechnicalOpinion): Observable<any> {
    let url = `${this.baseUri}/create`;
    console.log("(url): ", url);

    return this.http
      .post(url, technicalOpinion /*, { headers: this.headers }*/)
      .pipe(catchError(this.errorMgmt));
  }
  // Update TechnicalOpinion GiveOpinion
  updateTechnicalOpinion(data: TechnicalOpinion): Observable<any> {
    let url = `${this.baseUri}/update`;
    return this.http
      .put(url, data /*, { headers: this.headers }*/)
      .pipe(catchError(this.errorMgmt));
  }
  // ReadByProcess TechnicalOpinion
  getByProccess(process: Process): Observable<any> {
    let url = `${this.baseUri}/readbyprocess`;
    return this.http
      .post<ApiResponse<TechnicalOpinion[]>>(
        url,
        process /*, { headers: this.headers }*/
      )
      .pipe(
        map((res) => {
          return res.response || {};
        }),
        catchError(this.errorMgmt)
      );
  }
  // ReadByUser TechnicalOpinion
  getByUser(user: User): Observable<any> {
    let url = `${this.baseUri}/readbyuser`;
    return this.http
      .post<ApiResponse<TechnicalOpinion[]>>(
        url,
        user /*, { headers: this.headers }*/
      )
      .pipe(
        map((res) => {
          return res.response || {};
        }),
        catchError(this.errorMgmt)
      );
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
