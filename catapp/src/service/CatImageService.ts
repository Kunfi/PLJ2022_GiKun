import { AxiosInstance } from "axios";
import { defaultAxiosInstance } from "./Api";

const CatImageService = (api: AxiosInstance = defaultAxiosInstance) => ({
    getRandomCatImage: async () => {
        const data = await api.get("search");
        return data ["data"][0]["url"];
    }
});

export default CatImageService;