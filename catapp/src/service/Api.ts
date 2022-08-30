import {IAuthTokens, TokenRefreshRate, applyAuthTokenInterceptor} from "axios-jwt"
import axios, { AxiosInstance } from "axios"

const BASE_URL = "https://api.thecatapi.com/v1/images/search";

export const defaultAxiosInstance: AxiosInstance = axios.create({
    baseURL: BASE_URL,
});