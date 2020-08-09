export interface ApiResponse<T> {
  // abstract return
  errorCode: string;
  errorMessage: string;
  errorTag: string;
  response: T;
}
