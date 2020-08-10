export enum Role {
  Admin = "ADMIN",
  Screening = "SCREENING",
  Finisher = "FINISHER",
}

export class RoleTranslator {
  public static translate(role: string): string {
    let resRole: string;
    switch (role) {
      case Role.Admin:
        resRole = "Administrador";
        break;
      case Role.Screening:
        resRole = "Realizar Triagem";
        break;
      case Role.Finisher:
        resRole = "Finalizar Processo";
        break;
    }
    return resRole;
  }
}
