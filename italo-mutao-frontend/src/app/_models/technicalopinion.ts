import { Process } from "./process";
import { User } from "./user";

export interface TechnicalOpinion {
  id: number;
  hasTechnicalOpinionPending: boolean;
  technicalOpinion: string;
  process: Process;
  user: User;
}
