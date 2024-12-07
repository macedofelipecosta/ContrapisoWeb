package com.ContrapisoWeb.LogicaNegocio.Servicios;

import com.ContrapisoWeb.LogicaNegocio.Dominio.VideoClip;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ServicioVideoClips {
    List<VideoClip> videoClips;

    public ServicioVideoClips() {
        this.videoClips = new ArrayList<VideoClip>();
    }


}
