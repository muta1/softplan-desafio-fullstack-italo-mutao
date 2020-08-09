import { Role } from "./role";

export class User {
  id: number;
  logged: boolean;
  role?: Role;
  sessionCookieKey: string;
  sessionCookieValue: string;
  token?: string;
  name: string;
  password: string;
}
