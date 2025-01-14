package NahidaProject.Anime.controller;

import NahidaProject.Anime.service.AnimeService;
import NahidaProject.Anime.service.FileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/file")
@ResponseBody
@CrossOrigin
public class FileUploadController {

    @Resource
    AnimeService animeService;

    @Value("${Nahida.media-path}")
    String mediaPath;

    @RequestMapping(value = "/animePosterUpload/{a_id}", method = RequestMethod.POST)
    public Object animePosterUpload(@RequestParam("poster") MultipartFile poster, @PathVariable String a_id) {
        String posterName = mediaPath + "/main_image/" + a_id + ".png";
        return new FileUploadService().saveThisPoster(poster, posterName);
    }

    @RequestMapping(value = "/animeVideosUpload/{a_id}", method = RequestMethod.POST)
    public Object animeVideosUpload(@RequestParam("videos") MultipartFile videos, @PathVariable String a_id) {
        int a_set_tmp = animeService.findAnimeById(a_id).getA_set();
        String a_set;
        if (a_set_tmp >= 10 && a_set_tmp <= 99) {
            a_set = "0" + a_set_tmp;
        } else if (a_set_tmp < 10) {
            a_set = "00" + a_set_tmp;
        } else {
            a_set = "" + a_set_tmp;
        }
        String videoName = mediaPath + "/videos/" + a_id + "/" + a_id + "_" + a_set + ".mp4";
        return new FileUploadService().saveThisVideo(videos, videoName);
    }
}
