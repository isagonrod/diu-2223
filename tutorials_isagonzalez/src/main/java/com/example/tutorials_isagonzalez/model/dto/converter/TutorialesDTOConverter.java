package com.example.tutorials_isagonzalez.model.dto.converter;

import com.example.tutorials_isagonzalez.model.TutorialesVO;
import com.example.tutorials_isagonzalez.model.dto.TutorialesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TutorialesDTOConverter {
    private static final ModelMapper modelmapper = new ModelMapper();

    public static TutorialesDTO convertToDto(TutorialesVO tutoriales) {
        return modelmapper.map(tutoriales, TutorialesDTO.class);
    }

    public static List<TutorialesDTO> convertListToDto(List<TutorialesVO> tutorialsBd) {
        List<TutorialesDTO> tutorials = new ArrayList<>();
        for (TutorialesVO tutorial : tutorialsBd) {
            tutorials.add(modelmapper.map(tutorial, TutorialesDTO.class));
        }
        return tutorials;
    }

    public static TutorialesDTO convertToDto(Optional<TutorialesVO> byId) {
        return modelmapper.map(byId, TutorialesDTO.class);
    }
}
