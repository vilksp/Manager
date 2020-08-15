import axios from "axios";

const API_URL = "http://localhost:8080";

export const USER_NAME_SESSION_ATTRIBUTE_NAME = "authenticatedUser";
export const USER_ROLE = "role";
export const TOKEN_NAME = "token";

class Authorization {
  executeJwtAuthenticationService(username, password) {
    return axios.post(`${API_URL}/api/v1/auth/login`, {
      username,
      password,
    });
  }

  registerSuccessfulLoginForJwt(username, token) {
    sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username);
    this.setupAxiosInterceptors(this.createJWTToken(token));
  }

  registerJwtTT(token) {
    sessionStorage.setItem(TOKEN_NAME, this.createJWTToken(token));
  }

  registerUserRole(role) {
    sessionStorage.setItem(USER_ROLE, role);
  }

  createJWTToken(token) {
    return "Bearer " + token;
  }

  logout() {
    sessionStorage.removeItem(TOKEN_NAME);
    sessionStorage.removeItem(USER_ROLE);
    window.location.reload();
  }

  isAdminLoggedIn() {
    let role = sessionStorage.getItem(USER_ROLE);
    if (role === "admin") return true;
    return false;
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(TOKEN_NAME);
    if (user === null) return false;
    return true;
  }

  getLoggedInUserName() {
    let user = sessionStorage.getItem(TOKEN_NAME);
    if (user === null) return "";
    return user;
  }

  setupAxiosInterceptors(token) {
    axios.interceptors.request.use((config) => {
      if (this.isUserLoggedIn()) {
        config.headers.authorization = token;
      }
      return config;
    });
  }
}

export default new Authorization();
