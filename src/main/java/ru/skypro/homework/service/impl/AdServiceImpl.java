package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateAdsDTO;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.Collection;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdsMapper adsMapper;
    private final AdRepository adRepository;
    private final ImageService imageService;
    private final UserService userService;
    private final Logger log = LoggerFactory.getLogger(AdServiceImpl.class);

    @Override
    public Collection<AdsDTO> getAllAds(String title) {
        log.info("Request to receive all ads");
        Collection<Ad> ads;
        if (!isEmpty(title)){
            ads = adRepository.findByTitleLikeIgnoreCase(title);
        }
        else{
            ads = adRepository.findAll();
        }
        return adsMapper.adsToAdsListDto(ads);
    }

    @Override
    public AdsDTO createAd(CreateAdsDTO createAdsDTO, MultipartFile image, Authentication authentication) {
        Ad ad = adsMapper.adsDtoToAd(createAdsDTO);
        ad.setAuthor(userService.getAuthorizedUser(authentication));
        log.info("Request to create new ad");
        Image adImage;
        try {
            adImage = imageService.downloadImage(image);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить фото");
        }
        ad.setImage(adImage);
        adRepository.save(ad);
        log.info("Save new ad ID:" + ad.getId());

        return adsMapper.adToAdsDTO(ad);
    }

    @Override
    public FullAdsDto getFullAd(Long adId) {
        log.info("Request to get full info about ad");
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        return adsMapper.adToFullAdsDto(ad);
    }

    @Override
    public void deleteAd(Long adId) {
        log.info("Request to delete ad by id");
        if(adRepository.existsById(adId)){
            adRepository.deleteById(adId);
        }
        else {
            throw new AdNotFoundException();
        }
    }

    @Override
    public AdsDTO updateAd(CreateAdsDTO createAdsDTO, Long adId) {
        log.info("Request to update ad by id");
        if(adId == null || adRepository.findById(adId).isEmpty()){
            return null;
        }

        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        adsMapper.updateAds(createAdsDTO,ad);
        adRepository.save(ad);

        return adsMapper.adToAdsDTO(ad);
    }

    @Override
    public Collection<AdsDTO> getUserAllAds(Authentication authentication) {
        log.info("Request to get all user ads");
        Collection<Ad> ads;
        ads = adRepository.findAllAdsByAuthorId(userService.getAuthorizedUserDto(authentication).getId());
        return adsMapper.adsToAdsListDto(ads);
    }

    @Override
    public String updateImage(Long adId, MultipartFile image) throws IOException {
        log.info("Request to update image");
        Ad updateAd = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        long idImage = updateAd.getImage().getId();
        updateAd.setImage(imageService.downloadImage(image));
        imageService.deleteImage(idImage);
        adRepository.save(updateAd);
        return adsMapper.adToAdsDTO(updateAd).getImage();
    }

    @Override
    public byte[] getAdImage(Long adId){
        log.info("Get image of an AD with a ID:" + adId);
        return imageService.getImage(adRepository.findById(adId).orElseThrow(AdNotFoundException::new).getImage().getId());
    }
}
