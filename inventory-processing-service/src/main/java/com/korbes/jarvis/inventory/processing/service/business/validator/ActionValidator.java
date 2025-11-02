package com.korbes.jarvis.inventory.processing.service.business.validator;

import com.korbes.jarvis.inventory.processing.service.business.enums.ActionType;
import com.korbes.jarvis.inventory.processing.service.lib.error.ValidatorError;
import com.korbes.jarvis.inventory.processing.service.lib.exceptions.KorbesValidationException;
import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

import static com.korbes.jarvis.inventory.processing.service.lib.error.ErrorMessage.INVALID_ACTION;

@UtilityClass
public class ActionValidator {
  public static void validate(String action) throws KorbesValidationException {
    List<ValidatorError> validatorErrors = new ArrayList<>();
    if (ObjectUtils.isEmpty(ActionType.getByValue(action))) {
      validatorErrors.add(ValidatorError.builder().code("INVALID_ACTION").message(INVALID_ACTION + action).build());
    }
    if (CollectionUtils.isNotEmpty(validatorErrors)) {
      throw new KorbesValidationException("ACTION INVALID", validatorErrors);
    }
  }
}