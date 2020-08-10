export interface ApiResponse<T> {
  // abstract return
  success: boolean;
  errorMessage: string;
  errorTag: string;
  response: T;
}
