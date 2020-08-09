import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { environment } from "@environments/environment";
import { Process } from "@app/_models";

@Injectable({ providedIn: "root" })
export class ProcessService {
  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<Process[]>(`${environment.apiUrl}/process`);
  }

  getById(id: number) {
    return this.http.get<Process>(`${environment.apiUrl}/process/${id}`);
  }
}
